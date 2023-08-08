import {Component, ElementRef, Inject, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {divIcon, Marker} from 'leaflet';
import * as L from 'leaflet';
import { DOCUMENT } from '@angular/common';
import { HttpClient } from "@angular/common/http";
import 'leaflet.markercluster';
import {Vehicle} from "../../../core/models/vehicle.model";
import {TreeviewItem} from 'ngx-treeview';
import * as _ from 'lodash';
import {fromEvent, Subscription, takeUntil} from "rxjs";
import {VehicleService} from "../../../core/services/vehicle.service";
import {RefOrganService} from "../../../core/services/ref-organ.service";
import {RefOrgan} from "../../../core/models/ref-organ.model";
import {Localites} from "../../../core/models/localites.model";
import {FlotteMapService} from "../../../core/services/flotte-map.service";
import {LocalitesCivil} from "../../../core/models/localitesCivil.model";
import { Popup } from 'leaflet';
import {PositionService} from "../../../core/services/position.service";
import {Position} from "../../../core/models/position.model";


class Itemstreeview{
  id: string;
  text: string;
  value:Vehicle;
  prefOrgan: string;
  collapsed: boolean;
  checked: boolean;
  iStracking:boolean;
}

@Component({
  selector: 'app-flotteMap',
  templateUrl: './flotteMap.component.html',
  styleUrls: ['./flotteMap.component.scss']
})
export class AppFlotteMapComponent implements OnInit {

  @Input() textFilter : string[]
  @ViewChild('toggleButton') toggleButton: ElementRef;
  @ViewChild('menu') menu: ElementRef;

  @ViewChild("example-box") treeVlMenu: ElementRef;
  @ViewChild("divDialogFilter") divDialogFilter: ElementRef;
  @ViewChild("divHistoDate") divHistoDate: ElementRef;

  @ViewChild("div-vehicule-box") tableFilterAvence: ElementRef;
  @ViewChild("divVl") divVl: ElementRef;
  @ViewChild("divAlert") divAlert: ElementRef;
  @ViewChild("divUnite") divUnite: ElementRef;
  @ViewChild("divIncident") divIncident: ElementRef;
  @ViewChild("divCir") divCir: ElementRef;

  @ViewChild('searchInput', {static: false}) searchInput: ElementRef<HTMLInputElement>;
  vehicules :Vehicle[];
  localiteVl : Localites;
  localiteCivil : LocalitesCivil;
  refOrgans:RefOrgan[];
  ListRefOrgane: RefOrgan[];
  listVehicules :Vehicle[];
  element:any;
  map: L.Map;
  dialogInfoMapClick: boolean=true
  markerClusterGroup: L.MarkerClusterGroup;
  private positions: any;
  isHidden = false;
  listTable: any[] = [];
  sharedOpt: any[] = [];
  markerSelect = new Map<string, Marker>();
  vehiculeSelect = new Map<string, Itemstreeview>();
  interval = new Map<string,any>();
  private vehiculeFiltres :Itemstreeview[];
  items: TreeviewItem[]= [];
  notIsHidden = true;
  logoutIsHidden = true;
  menuIsHidden = true;
  isShow = true;
  searchIsHidden = false;
  private map_box = new Map();
  private subscriptions: Subscription[] = [];
  private dragSub: Subscription | undefined;
  filtreText:string[];
  measurementClick = false
  private _latLngs: L.LatLng[] = [];
  private _polyline: L.Polyline | null = null;
  _totalDistance = 0;
  private  doubleClickDetected = false;
  private points: L.CircleMarker[] = [];
  selectionMode = 'all';
  private selectedVlData : Vehicle[];
  allowSearch: boolean;
  constructor(@Inject(DOCUMENT) private document: any, private http: HttpClient,protected vehiculeService: VehicleService,private flotteMapsService: FlotteMapService,private positionService: PositionService,
              protected refOrganservice: RefOrganService,) {
    this.vehiculeFiltres = [];
    this.allowSearch = true;
    this.getRefOrgans(true);
    this.columnChooserModes = [{
      key: 'dragAndDrop',
      name: 'Drag and drop',
    }, {
      key: 'select',
      name: 'Select',
    }];
  }
  getRefOrgans(showNotification: boolean): void {

    this.subscriptions.push(
      this.refOrganservice.getRegOrgans().subscribe(
        (response: RefOrgan[]) => {
          this.refOrgans = response;
          this.ListRefOrgane =  this.updateListRefOrgane(this.refOrgans);
          this.getVehicule();
        },


      )
    );
  }
  public updateListRefOrgane(list:RefOrgan[]) {

    return list.map((elem) => ({

      id: elem.id,
      registrationNumber: elem.refOrganName,
      device: '',
      vehiculeDesc: '',
      vehicleType: '',
      vehicleBrand: '',
      vehicleModel: '',
      fuelType: '',
      vehiculeKmEstime: '',
      vehiculeMaxVitesse: '',
      driver: '',
      etatVehicule: '',
      vehiculeKmDate: '',
      frequence: '',
      indicatif: '',
      lastPosition: '',
      refOrgan : '',
      refOrganID: '',
      prefOrgan: elem.prefOrgan,

    }));
  }

  ngOnInit(): void {
    document.body.setAttribute('data-topbar', 'dark');
    document.body.setAttribute('data-layout', 'horizontal');
    this.getVehicule();

    this.map = L.map('map').setView([26.9526, -9.1771], 6);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'Map data © ROAD WAY LOCATION'
    }).addTo(this.map);
    this.markerClusterGroup = L.markerClusterGroup();
    this.map.addLayer(this.markerClusterGroup);
    //this.fetchPositions();
    this.listTable = [
      {
        id: 1,
        name: 'Vehicule',
        checked: false,
        icon:"./assets/icons/map-icon/Vehicule.png",
      },
      {
        id: 2,
        name: 'Units',
        checked: false,
        icon:"./assets/icons/map-icon/Units.png",
      },
      {
        id: 3,
        name: 'Incidents',
        checked: false,
        icon:"./assets/icons/map-icon/Incidents.png",

      },
      {
        id: 4,
        name: 'Alert',
        checked: false,
        icon:"./assets/icons/map-icon/Alert.png",
      }
    ];
    this.sharedOpt = [
      {
        id: 1,
        name: 'Moyens Roulant',
        checked: true,
        icon: './assets/icons/map-icon/road.png'
      },
      {
        id: 2,
        name: 'Moyens Aerien',
        checked: false,
        icon: './assets/icons/map-icon/aircraft.png'
      },
      {
        id: 3,
        name: 'Moyens Maritime',
        checked: false,
        icon: './assets/icons/map-icon/maritime-rescue.png'
      },
      {
        id: 5,
        name: 'Positions',
        checked: false,
        icon: './assets/icons/map-icon/position.png'
      },
      {
        id: 6,
        name: 'Units',
        checked: true,
        icon: './assets/icons/map-icon/Units.png'
      },
    ]


    this.map.on('dblclick',(e:L.LeafletMouseEvent)=>{
      if(!this.measurementClick){
        this.dialogInfoMapClick=false;
        this.onMapClick(e.latlng);
        this.divCir.nativeElement.style.display="block";
      }


    });


    this.map.on('click', (e) => {
      if (this.measurementClick) {
        if (this.doubleClickDetected) {
          return;
        }

        this._totalDistance = 0;
        this._latLngs.push(e.latlng);

        let point = L.circleMarker(e.latlng, { radius: 5, color: 'blue', fillOpacity: 1 })
          .addTo(this.map)
          .bindPopup('Point ' + this._latLngs.length + '<br/>' + e.latlng)
          .openPopup();
        this.points.push(point);

        if (this._latLngs.length >= 2) {
          if (this._polyline) {
            this.map.removeLayer(this._polyline);
          }

          this._polyline = L.polyline(this._latLngs, {
            color: 'black',
            dashArray: '5, 10'
          });

          this._polyline.addTo(this.map);

          for (let i = 0; i < this._latLngs.length - 1; i++) {
            let distance = this.map.distance(this._latLngs[i], this._latLngs[i + 1]);
            this._totalDistance += distance;
          }

          let distanceInKilometers = this._totalDistance / 1000;

          const element = document.getElementById('length');
          if (element) {
            element.innerText = distanceInKilometers.toFixed(2) + ' km';
          }

        }
      }
    });

    this.map.on('mousemove', (e) => {
      if (this._latLngs.length >= 1 && !this.doubleClickDetected) {
        // Remove the previous polyline if it exists
        if (this._polyline) {
          this.map.removeLayer(this._polyline);
        }

        // Add the updated polyline with the new cursor position
        let updatedLatLngs = this._latLngs.slice(); // Create a copy of _latLngs array
        updatedLatLngs.push(e.latlng);

        this._polyline = L.polyline(updatedLatLngs, {
          color: 'black',
          dashArray: '5, 10'
        });

        this._polyline.addTo(this.map);
      }
    });

    this.map.on('dblclick', () => {
      this.doubleClickDetected = true;
    });


    const element = document.getElementById('yourElement');
    if (element) {
      element.style.display = 'none';
    }



  }

  searchResults: string[] = []; // Variable to store the search results

  // Dummy data for demonstration, replace with your actual data and search logic
  allOptions: string[] = ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'];

  // Function to handle the search input change
  onSearchInputChange(event: Event) {
    const query = (event.target as HTMLInputElement).value; // Safely access the input value
    // Perform the search logic here, filter the options based on the query
    this.searchResults = this.allOptions.filter(option => option.toLowerCase().includes(query.toLowerCase()));
  }

  // Function to handle the selection of a search result
  onSearchResultSelected(result: string) {
    // Do something with the selected result, like filtering the data or updating the UI
    console.log('Selected result:', result);
  }
  private onMapClick(latLng:L.LatLng) {
    this.getLocalitePosition(latLng);
  }
  getLocalitePosition(latLng:L.LatLng){
    this.flotteMapsService.findLocalite(latLng.lng,latLng.lat).subscribe((localiteVl:Localites)=>{

      this.localiteVl= localiteVl;
    });
    this.flotteMapsService.findLocaliteCivil(latLng.lng,latLng.lat).subscribe((localiteCivil:LocalitesCivil)=>
    {
      //console.log(localiteCivil);
      this.localiteCivil= localiteCivil;
    })

  }
  toggleArrange() {
    this.isHidden = !this.isHidden;
  }

  addMarkers(): void {
    const customIcon = divIcon({
      className: 'custom-icon',
      html: '<img src="assets/icons/marker/car.png" alt="Car" width="32" height="32">',
      iconSize: [32, 32]
    });
    console.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
    console.log(this.positions)
    const markers = this.positions?.map((position:any) =>{

      const marker =L.marker([position.lastPosition.latitude, position.lastPosition.longitude], { icon: customIcon })

      marker.bindPopup(this.getPopupContent(position));

      return marker;
      }

    );

    this.markerClusterGroup.addLayers(markers);
  }
  getPopupContent(position: any): string {

    const content = `
      <div>
  <table class="table table-centered datatable dt-responsive nowrap"
         style="border-collapse: collapse; border-spacing: 0; width: 100%;" id="tabMission">
    <thead class="thead-light">
    <tr>
      <td  colspan="3"><b>Registration Number :</b> ${position.registrationNumber}</td>


    </tr>
    <tr>
      <td colspan="3"><b>Conducteur :</b></td>

    </tr>

    <tr>
      <td colspan="3"><b>Description :</b> ${position.vehiculeDesc}</td>

    </tr>
    <tr>
      <td colspan="3"><b>Device :</b> ${position.device.deviceID}</td>

    </tr>

    <tr>
      <td colspan="3"><b>Entité :</b> Entité A</td>

    </tr>
    <tr>
      <td><b>Actions :</b></td>
      <td style="cursor: pointer"><img src="./assets/icons/map-icon/delivery.png"  /></td>
      <td >Historique :</td>
    </tr>

    </thead>
    <tbody>

    </tbody>
  </table>


  </div>

  `;
    return content;
  }
  showHistoricalPositions(deviceid: string, startDate: string, endDate: string): void {
    this.positionService.findByDeviceIDanBetweenDateDanddateF(deviceid, startDate, endDate).subscribe(
      (positions: Position[]) => {
        this.markerClusterGroup.clearLayers();
        const customIcon = divIcon({
          className: 'custom-icon',
          html: '<img src="assets/icons/marker/car.png" alt="Car" width="32" height="32">',
          iconSize: [32, 32]
        });

        const markers = positions.map((position: any) => {
          const marker = L.marker([position.latitude, position.longitude], { icon: customIcon })
          return marker;
        });

        // Create a polyline using the positions
        const latLngs: L.LatLng[] = positions.map((position: any) => L.latLng(position.latitude, position.longitude));
        const polyline = L.polyline(latLngs, { color: 'blue' }).addTo(this.map);

        // Fit the map bounds to show the polyline
        if (latLngs.length > 0) {
          const bounds = L.latLngBounds(latLngs);
          this.map.fitBounds(bounds);
        };

      },
      (error: any) => {
      }
    );
  }

  public getVehicule(): void {
    this.subscriptions.push(
      this.vehiculeService.getVehicles().subscribe(
        (response: Vehicle[]) => {
          this.positions = response
          this.addMarkers();

        },
      )

    );

  }

  arrange() {
    const divs = ['4', '1', '3', '2'];
    const transformValue = "translate3d(0px, 1px, 0px)";

    divs.forEach(element => {
      document.getElementById(element)!.style.transform = transformValue;
    });
  }

  changeCheckbox(item: { checked: any; id: number; }) {

    this.UpdateStatusTable(item);
    if(item.checked) {
      if (item.id === 1) {
        this.divVl.nativeElement.style.display = "block";
      } else  if (item.id === 2) {
        this.divUnite.nativeElement.style.display = "block";
      } if (item.id === 3) {
        this.divIncident.nativeElement.style.display = "block";
      } else if (item.id === 4) {
        this.divAlert.nativeElement.style.display = "block";
      }
    } else {
      if (item.id === 1) {
        this.divVl.nativeElement.style.display = "none";
      } else  if (item.id === 2) {
        this.divUnite.nativeElement.style.display = "none";
      } if (item.id === 3) {
        this.divIncident.nativeElement.style.display = "none";
      } else if (item.id === 4) {
        this.divAlert.nativeElement.style.display = "none";
      }
    }
  }

  UpdateStatusTable(item: { checked: any; id: any; }){
    this.listTable.map(tb =>{
      if(tb.id === item.id){
        tb.checked=item.checked;
      }
    });
  }
  UpdateStatusTableClose(id:number){
    this.listTable.map(tb =>{
      if(tb.id === id){
        tb.checked=false;
      }
    });
  }
  closeDialogFilter(event: { style: { display: string; }; id: string; }){
    event.style.display="none";
    const idDiv = parseInt(event.id, 10);
    this.UpdateStatusTableClose(idDiv);
  }
  myStop(id: string) {
    clearInterval(this.interval.get(id));
    this.interval.delete(id)
  }
  public updateListVl(list: any[],ischecked:boolean,iStracking:boolean) {

    return list.map((elem) => (
      this.updateVl(elem,ischecked,iStracking)

    ));
  }
  public updateVl(vl: { id: any; registrationNumber: any; prefOrgan: any; },ischecked:boolean,iStracking:boolean) {

    return {
      id: vl.id,
      text: vl.registrationNumber,
      value:vl,
      prefOrgan: vl.prefOrgan,
      collapsed: true,
      checked: ischecked,
      iStracking:iStracking


    } ;
  }

  vehsFiltreUpdateItems:Itemstreeview[];
  device: string="354018114581451";
  histordateD :  Date = new Date();
  histordateF:Date = new Date();
  dialogHidden: boolean=true;
  dialogHiddenDate: boolean=true;
  loading: any;
  shearchHidden: boolean=false;
  isSearchBoxVisible: boolean = false;
  selectedRowKeys:any[]=[];
  columnChooserModes : any;
  clossDialogFilter(){

    this.dialogHidden = true;

  }
  clossDialogDate(){

    this.dialogHiddenDate = true;

  }
  getVehiculeFiltre(vehs :  Vehicle[]) {
    if (this.markerSelect.size >0  && this.vehiculeSelect.size >0 ) {
      this.vehiculeSelect.forEach(imm=>{
        this.markerSelect.delete(imm.id);
        this.vehiculeSelect.delete(imm.id);

        this.myStop(imm.id);

      })

    }

    this.onSelectedChangeVl(vehs);
    const filteredRegion :  string[] = []
    const filteredCie :  string[] = []
    const filteredBt :  string[] = []

    this.vehsFiltreUpdateItems=[];

    this.vehsFiltreUpdateItems=this.updateListVl(vehs,true,false);
    this.vehsFiltreUpdateItems.forEach((vlFilter) => {
      if (vlFilter.value.registrationNumber && vlFilter.value.registrationNumber.includes('Region')) {
        filteredRegion.push(vlFilter.text.split("Region")[1].trim());
      }

      else if (vlFilter.value.registrationNumber && vlFilter.value.registrationNumber.includes('Compagnie')) {
        filteredCie.push(vlFilter.text.split("Compagnie")[1].trim());
      }

      else if (vlFilter.value.registrationNumber && vlFilter.value.registrationNumber.includes('BT')) {
        filteredBt.push(vlFilter.text.split("BT")[1].trim());
      }

      if(!_.isEmpty(vlFilter.value.device) && vlFilter.value.device !== null){

        if(!(this.vehiculeFiltres.filter(x => x.id === vlFilter.id).length>0)){
          this.vehiculeFiltres.push(vlFilter);
        }

      }
    });

    if(filteredRegion.length>0){
      const newFilteredRegion :  string[]  = filteredRegion.filter((value, index, array) => {
        return array.indexOf(value) === index;
      });

    }else if(filteredCie.length>0){
    }else if(filteredBt.length>0){
    }

    this.vehicules=this.vehiculeFiltres;

  }

  onSelectedChangeVl(immVls:Vehicle[]): void {
    const markers = L.markerClusterGroup({
      maxClusterRadius: 40,
      spiderfyOnMaxZoom: true,
      showCoverageOnHover: true,
      zoomToBoundsOnClick: true
    });}


  getRefOrgs(obj: any): TreeviewItem[] {

    const childrenCategory = new TreeviewItem({collapsed:false,disabled:false,checked:false,text: '', value: undefined, children:obj });

    return [childrenCategory];

  }


  closeAll() {
    this.isHidden = true;
    this.isShow = true;
    this.logoutIsHidden = true;
    this.notIsHidden = true;
    this.menuIsHidden = true;
    this.searchIsHidden = true;
  }

  clickUnite() {
    const element = document.querySelector('.div-unite');
    if (element instanceof Element) {
      this.initDrag(element);
    } else {
      console.error("Element '.div-unite' not found.");
    }
  }

  initDrag(element: Element): void {
    const handle = element.querySelector('.handle');
    if (!(handle instanceof Element)) {
      console.error("Handle element not found.");
      return;
    }
    let dragSub: Subscription | undefined;
    // main logic will come here
    // 1
    const dragStart$ = fromEvent<MouseEvent>(handle, "mousedown");
    const dragEnd$ = fromEvent<MouseEvent>(document, "mouseup");
    const drag$ = fromEvent<MouseEvent>(document, "mousemove").pipe(
      takeUntil(dragEnd$)
    );

    // 2
    let initialX: number,
      initialY: number;
    let current = {
      currentX : 0,
      currentY: 0
    }
    if(this.map_box.has(element)){
      current=this.map_box.get(element)
    }

    this.map_box.get(element);



    // 3
    const dragStartSub = dragStart$.subscribe((event: MouseEvent) => {
      initialX = event.clientX - current.currentX;
      initialY = event.clientY - current.currentY;
      element.classList.add('free-dragging');

      // 4
      dragSub = drag$.subscribe((event: MouseEvent) => {
        event.preventDefault();

        current.currentX = event.clientX - initialX;
        current.currentY = event.clientY - initialY;

        // @ts-ignore
        element.style.transform = "translate3d(" + current.currentX + "px, " + current.currentY + "px, 0)",
          // @ts-ignore
          element.style.zIndex = "1000",

          this.map_box.set(element, current);

      });
    });

    // 5
    const dragEndSub = dragEnd$.subscribe(() => {
      initialX = current.currentX;
      initialY = current.currentY;
      element.classList.remove('free-dragging');
      if (dragSub) {
        dragSub.unsubscribe();
      }
    });

    // 6
    this.subscriptions.push.apply(this.subscriptions, [
      dragStartSub,
      dragEndSub
    ]);

  }

  getFiltreText(txtFilter:string[]){
    this.filtreText=txtFilter

  }

  clickAlert() {
    const element = document.querySelector('.div-alert');
    if (element instanceof Element) {
      this.initDrag(element);
    } else {
      console.error("Element '.div-alert' not found.");
    }
  }

  clickIncident() {
    const element = document.querySelector('.div-incident');
    if (element instanceof Element) {
      this.initDrag(element);
    } else {
      console.error("Element '.div-incident' not found.");
    }
  }

  clickVehicule() {
    const element = document.querySelector('.div-vehicule-box2');
    if (element instanceof Element) {
      this.initDrag(element);
    } else {
      console.error("Element '.div-incident' not found.");
    }
  }

  handleClickMeasurement(){
    this.measurementClick= !this.measurementClick

    this.element = document.getElementById('length');
    if (this.measurementClick==true) {
      this.element.textContent = '';
      this.element.style.display = 'initial';
      this.doubleClickDetected = false;
    }else{
      this.element.style.display = 'none';
      if (this._polyline) {
        this.map.removeLayer(this._polyline);
        this._polyline = null;
      }



      this.points.forEach(point => {
        point.removeFrom(this.map);
      });

      this.points.forEach(point => {
        this.map.removeLayer(point); // Remove each CircleMarker individually
      });
      this.points=[]

      this._latLngs=[]

    }
  }

  clickHisto() {

  }
  filterDate() {


  }

  clickFilter() {

  }

  filtreAvanceModal(openFiltreAvance: TemplateRef<any>) {

  }

  clearFiltreAvance() {

  }

  iconTrack(item: any) {

  }


  toggleSearchBox() {
    this.isSearchBoxVisible = !this.isSearchBoxVisible;
  }

  onSelectionChanged(e: any) {

    const selectedRowKeys = e.component.getSelectedRowKeys(this.selectionMode);

    const deselectedData = this.vehicules.filter(item => !selectedRowKeys.includes(item.id));


    this.selectedVlData = e.component.getSelectedRowsData(this.selectionMode);

  }
}
