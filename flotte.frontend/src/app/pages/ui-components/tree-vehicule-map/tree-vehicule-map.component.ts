import {Component, OnInit, Output, ViewChild, EventEmitter, Input} from '@angular/core';
import {DxTreeListModule, DxCheckBoxModule, DxSelectBoxModule, DxTreeListComponent} from 'devextreme-angular';
import {Vehicle} from '../../../core/models/vehicle.model';
import {VehicleService} from '../../../core/services/vehicle.service';
import {Subscription} from 'rxjs';
import {RefOrgan} from '../../../core/models/ref-organ.model';
import {RefOrganService} from '../../../core/services/ref-organ.service';



@Component({
  selector: 'app-tree-vehicule-map',
  templateUrl: './tree-vehicule-map.component.html',
  styleUrls: ['./tree-vehicule-map.component.scss']
})
export class TreeVehiculeMapComponent implements OnInit {
  @Input() textFilter : string[]
  @Output() vehiculeChanged = new EventEmitter<Vehicle[]>();
  @Output() filterText = new EventEmitter<string[]>();

  @ViewChild(DxTreeListComponent, { static: false }) treeList: DxTreeListComponent;

vehicules :Vehicle[];
  refOrgans:RefOrgan[];
  ListRefOrgane: Vehicle[];
  listVehicules :Vehicle[];
  allowSearch : boolean;
  columnChooserModes : any;
  private subscriptions: Subscription[] = [];
  //recursiveSelectionEnabled = true;
selectedRowKeys:any[]=[];
  selectAll = false;
  selectedVehicules = 'No vl has been selected';
  selectionMode = 'all';
  private selectedVlData : Vehicle[];


  constructor(protected vehiculeService: VehicleService,
              protected refOrganservice: RefOrganService
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
  public getVehicule(): void {
    this.subscriptions.push(
      this.vehiculeService.getVehicles().subscribe(
        (response: Vehicle[]) => {
          this.vehicules = response


        },
      )

    );

  }

  getRefOrgans(showNotification: boolean): void {

    this.subscriptions.push(
      this.refOrganservice.getRegOrgans().subscribe(
        (response: RefOrgan[]) => {
          this.refOrgans = response;
          console.log( this.refOrgans)
         // this.ListRefOrgane =  this.updateListRefOrgane(this.refOrgans);

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


  onSelectionChanged(e: any) {

    const selectedRowKeys = e.component.getSelectedRowKeys(this.selectionMode);

    const deselectedData = this.vehicules.filter(item => !selectedRowKeys.includes(item.id));


    this.selectedVlData = e.component.getSelectedRowsData(this.selectionMode);

  }
  onChange(): void {

    this.vehiculeChanged.emit(this.selectedVlData);

    this.filterText.emit(this.textFilter);

  }

}
