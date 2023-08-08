import {Component} from '@angular/core';

import {Subscription} from "rxjs";
import {Brand} from "../../../core/models/device-brand.model";
import {HttpErrorResponse} from "@angular/common/http";
import DataSource from "devextreme/data/data_source";
import {DeviceModelService} from "../../../core/services/device-model.service";
import {DeviceModel} from "../../../core/models/device-model.model";


@Component({
  selector: 'app-modelDevice',
  templateUrl: './modelDevice.component.html',
})
export class AppModelDeviceComponent {
  columns: any[];
  model: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];
  constructor( protected deviceModelService: DeviceModelService) {
    this.columns = [
      {dataField: 'deviceModel', dataType: "string", caption: 'Device Model'},
      {dataField: 'brand.deviceBrand', dataType: "string", caption: 'Marque'},
      {dataField: 'brand.deviceCategory.deviceCategory', dataType: "string", caption: 'Catégorie'}
    ];

  }
  ngOnInit(): void {
    this.getBrand(true);
  }
  public getBrand(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.deviceModelService.getGpsModel().subscribe(
        (response: DeviceModel[]) => {
          this.model = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.model, // Initially, there's no data
              key: 'id' // Replace with the primary key field of your Device model
            },
            paginate: true // Enable server-side pagination
          });
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoading = false;
        }
      )
    );
  }


}
