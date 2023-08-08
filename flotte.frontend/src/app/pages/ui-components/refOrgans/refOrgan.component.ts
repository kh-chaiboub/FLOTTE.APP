import {ChangeDetectorRef, Component, Input} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";
import {DriverService} from "../../../core/services/driver.service";
import {Driver} from "../../../core/models/driver.model";
import {HttpErrorResponse} from "@angular/common/http";
import {Vehicle} from "../../../core/models/vehicle.model";
import {VehicleService} from "../../../core/services/vehicle.service";
import {RefOrganService} from "../../../core/services/ref-organ.service";
import {RefOrgan} from "../../../core/models/ref-organ.model";


@Component({
  selector: 'app-refOrgan',
  templateUrl: './refOrgan.component.html',
})
export class AppRefOrganComponent {
  @Input() textFilter : string[]
  columns: any[];
  driver: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  vehicules :Vehicle[];
  refOrgans:RefOrgan[];
  ListRefOrgane: RefOrgan[];
  listVehicules :Vehicle[];
  columnChooserModes : any;
  allowSearch : boolean;
  selectionMode = 'all';
  private selectedVlData : Vehicle[];
  selectedRowKeys:any[]=[];
  private subscriptions: Subscription[] = [];
  constructor(protected vehiculeService: VehicleService,
              protected refOrganservice: RefOrganService,) {
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
          this.listVehicules=this.ListRefOrgane.concat(this.vehicules);

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


}
