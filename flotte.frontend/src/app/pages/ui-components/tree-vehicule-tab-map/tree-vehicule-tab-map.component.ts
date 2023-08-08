import {Component, OnInit, Output, ViewChild, EventEmitter, Input} from '@angular/core';
import {DxTreeListComponent} from 'devextreme-angular';
import {Vehicle} from '../../../core/models/vehicle.model';
import {VehicleService} from '../../../core/services/vehicle.service';
import {Subscription} from 'rxjs';
import {RefOrgan} from '../../../core/models/ref-organ.model';
import {RefOrganService} from '../../../core/services/ref-organ.service';


@Component({
  selector: 'app-tree-vehicule-tab-map',
  templateUrl: './tree-vehicule-tab-map.component.html',
  styleUrls: ['./tree-vehicule-tab-map.component.scss']
})
export class TreeVehiculeTabMapComponent implements OnInit {
  @Input() textFilter : string[]
  @Output() vehiculeChanged = new EventEmitter<Vehicle[]>();
  @Output() filterText = new EventEmitter<string[]>();

  @ViewChild(DxTreeListComponent, { static: false }) treeList: DxTreeListComponent;


vehicules :Vehicle[];
  refOrgans:RefOrgan[];
  ListRefOrgane: { vehiculeDesc: string; refOrganID: string; indicatif: string; refOrgan: string; vehiculeMaxVitesse: string; vehiculeKmDate: string; etatVehicule: string; vehicleBrand: string; fuelType: string; driver: string; registrationNumber: any; vehicleModel: string; vehiculeKmEstime: string; lastPosition: string; id: any; frequence: string; device: string; vehicleType: string }[];
  listVehicules :{ vehiculeDesc: string; refOrganID: string; indicatif: string; refOrgan: string; vehiculeMaxVitesse: string; vehiculeKmDate: string; etatVehicule: string; vehicleBrand: string; fuelType: string; driver: string; registrationNumber: any; vehicleModel: string; vehiculeKmEstime: string; lastPosition: string; id: any; frequence: string; device: string; vehicleType: string }[];
  allowSearch : boolean;
  columnChooserModes : any;
  private subscriptions: Subscription[] = [];

selectedRowKeys:any[]=[];
  selectAll = false;
  selectedVehicules = 'No vl has been selected';
  selectionMode = 'all';
  private selectedVlData : Vehicle[];


  constructor(protected vehiculeService: VehicleService,
              protected refOrganservice: RefOrganService,
              ) {
    this.allowSearch = true;
    this.columnChooserModes = [{
      key: 'dragAndDrop',
      name: 'Drag and drop',
    }, {
      key: 'select',
      name: 'Select',
    }];


  }

  ngOnInit(): void {
    this.getRefOrgans(true);
  }

  getRefOrgans(showNotification: boolean): void {

    this.subscriptions.push(
      this.refOrganservice.getRegOrgans().subscribe(
        (response: RefOrgan[]) => {
          this.refOrgans = response;
          console.log( this.refOrgans)
          this.ListRefOrgane =  this.updateListRefOrgane(this.refOrgans);
          console.log(this.ListRefOrgane)


          this.getVehicule();

        },

      )
    );

  }

  public getVehicule(): void {
    this.subscriptions.push(
      this.vehiculeService.getVehicles().subscribe(
        (response: Vehicle[]) => {
          // this.vehicules = this.updateListVl(response);
          this.vehicules = response
         // console.log(this.vehicules )
      //this.listVehicules=this.ListRefOrgane.concat(this.vehicules);
   console.log(this.listVehicules)

        },

      )

    );

  }
  public updateListRefOrgane(list: any[]) {

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
      //prefOrgan: elem.prefOrgan,

    }));


  }

  onSelectionChanged(e: any) {

    const selectedRowKeys = e.component.getSelectedRowKeys(this.selectionMode);

    const deselectedData = this.vehicules.filter(item => !selectedRowKeys.includes(item.id));
    this.selectedVlData = e.component.getSelectedRowsData(this.selectionMode);

  }
  onChange(): void {
    //console.log(this.selectedVlData)
    this.vehiculeChanged.emit(this.selectedVlData);

    this.filterText.emit(this.textFilter);

  }
}
