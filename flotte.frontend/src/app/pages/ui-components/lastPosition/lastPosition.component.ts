import {ChangeDetectorRef, Component} from '@angular/core';
import {VehicleService} from "../../../core/services/vehicle.service";
import {LastPositionService} from "../../../core/services/last-position.service";
import {LastPosition} from "../../../core/models/last-position.model";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";

export interface Section {
  name: string;
  updated: Date;
}

@Component({
  selector: 'app-lastPosition',
  templateUrl: './lastPosition.component.html',
})
export class AppLastPositionComponent {
  dataSource: DataSource;
  columns: any[];

  public breadCrumbItems: Array<{}>;
  public lastPositions :LastPosition[];
  isLoading = false;
  private subscriptions: Subscription[] = [];
  pagingOptions = {
    pageSize: 30,
    currentPage: 0,
    totalCount: 50
  };
  collapsed = false;
  constructor(protected lastPositionService:LastPositionService,private changeDetectorRefs: ChangeDetectorRef,
              protected vehiculeService: VehicleService) {
    this.columns = [
      {dataField: 'registrationNumber', dataType: "string", caption: 'Matricule'},
      {dataField: 'device.deviceID', dataType: "string", caption: 'Device ID'},
      {dataField: 'lastPosition.deviceTime', dataType: "date", caption: 'Date Position'},
      {dataField: 'lastPosition.speed', dataType: "number", caption: 'Vitesse km/h'},
      {dataField: 'lastPosition.localite.localite', dataType: "string", caption: 'Localite'},
    ];

    this.getLastPositions(true, 0, 20);
  }
  public getLastPositions(showNotification: boolean,page:number,size:number): void {

    this.isLoading = true;
    this.subscriptions.push(
      this.vehiculeService.getVehiclesProjection(page,size).subscribe(
        (response: LastPosition[]) => {
          this.lastPositions = response;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.lastPositions,
              key: 'id'
            },
            paginate: true
          });


          this.isLoading = false;
          this.changeDetectorRefs.detectChanges();
          this._fetchData(this.lastPositions);

        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoading = false;
        }
      )
    );
  }
  private _fetchData(brandsata: LastPosition[]) {
    this.isLoading = true;
    this.lastPositions = brandsata;
  }

  loadDataIndexChange(e: number) {
    this.pagingOptions.currentPage = e;
    this.getLastPositions(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }

  loadDataSizeChange(e: number) {
    this.pagingOptions.pageSize = e;
    this.pagingOptions.currentPage = 0;
    this.getLastPositions(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }

  contentReady = (e: any) => {
    if (!this.collapsed) {
      this.collapsed = true;
      e.component.expandRow(['EnviroCare']);
    }
  }
}
