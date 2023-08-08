import { Component } from '@angular/core';
import DataSource from "devextreme/data/data_source";
import {DeviceService} from "../../../core/services/device.service";
import {Device} from "../../../core/models/device.model";
import {HttpErrorResponse} from "@angular/common/http";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-listDevice',
  templateUrl: './listDevice.component.html'
})
export class AppListDeviceComponent {
  columns: any[];
  public devices:Device[];
  isLoading = false;
  dataSource:DataSource;
  private subscriptions: Subscription[] = [];
  readonly allowedPageSizes = [5, 10, 'all'];

  readonly displayModes = [{ text: "Display Mode 'full'", value: 'full' }, { text: "Display Mode 'compact'", value: 'compact' }];

  displayMode = 'full';

  showPageSizeSelector = true;
  collapsed = false;
  showInfo = true;

  showNavButtons = true;
  pagingOptions = {
    pageSize: 30,
    currentPage: 0,
    totalCount: 50
  };

  constructor(protected deviceService: DeviceService) {
    this.columns = [
      { dataField: 'deviceID',dataType:"string", caption: 'Device ID' },
      { dataField: 'state',dataType:"string", caption: 'State' },
      { dataField: 'imei',dataType:"string", caption: 'Imei' },
      { dataField: 'active', dataType:"boolean",caption: 'active' },
      { dataField: 'phoneNumber',dataType:"string", caption: 'Phone Number' },
      { dataField: 'lastPosition',dataType:"string", caption: 'Last Position' },
    ];


  }
  ngOnInit(): void {

    this.getDevices(true, 0, 20);

  }

  public getDevices(showNotification: boolean,page:number,size:number): void {

    this.isLoading = true;
    this.subscriptions.push(
      this.deviceService.getDeviceProjection(page,size).subscribe(
        (response: Device[]) => {
          this.devices=response;
          this._fetchData(this.devices);
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.devices, // Initially, there's no data
              key: 'id' // Replace with the primary key field of your Device model
            },
            paginate: true // Enable server-side pagination
          });
          console.log(this.devices)
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
  private _fetchData(brandsata: Device[]) {
    this.isLoading = true;
    this.devices = brandsata;
  }

  loadDataIndexChange(e: number) {
    this.pagingOptions.currentPage = e;
    this.getDevices(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }

  loadDataSizeChange(e: number) {
    this.pagingOptions.pageSize = e;
    this.pagingOptions.currentPage = 0;
    this.getDevices(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }


}
