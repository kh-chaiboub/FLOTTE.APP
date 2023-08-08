import {Component} from '@angular/core';

import {Subscription} from "rxjs";
import {DeviceModel} from "../../../core/models/device-model.model";
import {HttpErrorResponse} from "@angular/common/http";
import DataSource from "devextreme/data/data_source";
import {TypeService} from "../../../core/services/typeVl.service";
import {VehicleTypes} from "../../../core/models/vehicle-types.model";


@Component({
  selector: 'app-typeVl',
  templateUrl: './typeVl.component.html',
})
export class AppTypeVlComponent {
  columns: any[];
  typevl: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];
  constructor( protected typeService: TypeService) {
    this.columns = [
      {dataField: 'vehiculeType', dataType: "string", caption: 'Type moyen'}
    ];

  }
  ngOnInit(): void {
    this.getBrand(true);
  }
  public getBrand(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.typeService.getTypes().subscribe(
        (response: VehicleTypes[]) => {
          this.typevl = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.typevl, // Initially, there's no data
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
