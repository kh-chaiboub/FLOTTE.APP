import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {VehicleService} from "../../../core/services/vehicle.service";
import DataSource from "devextreme/data/data_source";
import {Subscription} from "rxjs";
import {Vehicle} from "../../../core/models/vehicle.model";
import {HttpErrorResponse} from "@angular/common/http";



@Component({
  selector: 'app-icons',
  templateUrl: './vehicle.component.html',
})
export class AppVehicleComponent implements OnInit {
  columns: any[];
  vehicles: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];
  showInfo = true;

  showNavButtons = true;
  pagingOptions = {
    pageSize: 30,
    currentPage: 0,
    totalCount: 50
  };
  readonly allowedPageSizes = [5, 10, 'all'];

  readonly displayModes = [{ text: "Display Mode 'full'", value: 'full' }, { text: "Display Mode 'compact'", value: 'compact' }];

  displayMode = 'full';

  showPageSizeSelector = true;
  constructor(public vehicleService: VehicleService,private changeDetectorRefs: ChangeDetectorRef){

    this.columns = [
      { dataField: 'registrationNumber',dataType:"string", caption: 'registration Number' },
      { dataField: 'vehiculeDesc',dataType:"string", caption: 'vehicule' },
      { dataField: 'vehiculeMaxVitesse', dataType:"number",caption: 'vehicule Max Vitesse' },
      { dataField: 'etatVehicule.vehicleStatus',dataType:"string", caption: 'Etat' },
      { dataField: 'device.deviceID',dataType:"string", caption: 'device ID' },
    ];

}

  ngOnInit(): void {
    this.getVehicles(true, 0, 20);
  }
  public getVehicles(showNotification: boolean,page:number,size:number): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.vehicleService.getVehiclesProjection(page,size).subscribe(
        (response: Vehicle[]) => {
          this.vehicles = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.vehicles, // Initially, there's no data
              key: 'id' // Replace with the primary key field of your Device model
            },
            paginate: true // Enable server-side pagination
          });

          this.changeDetectorRefs.detectChanges();
          this._fetchData(this.vehicles);
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoading = false;
        }
      )
    );
  }
  contentReady = (e: any) => {
    if (!this.collapsed) {
      this.collapsed = true;
      e.component.expandRow(['EnviroCare']);
    }
  }
  private _fetchData(vehicleData: any[]) {
    this.isLoading = true;
    this.vehicles = vehicleData;
  }

  loadDataIndexChange(e: number) {
    this.pagingOptions.currentPage = e;
    console.log( this.pagingOptions)
    this.getVehicles(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
    //this.getLastPositions(true);
  }

  loadDataSizeChange(e: number) {
    this.pagingOptions.pageSize = e;
    this.pagingOptions.currentPage = 0;
    console.log( this.pagingOptions)
    this.getVehicles(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }



}
