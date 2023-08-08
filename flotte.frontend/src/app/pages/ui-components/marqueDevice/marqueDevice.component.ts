import {ChangeDetectorRef, Component} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";
import {CategoryService} from "../../../core/services/category.service";
import {BranddevisService} from "../../../core/services/brand-devices.service";
import {DeviceCategory} from "../../../core/models/device-category.model";
import {HttpErrorResponse} from "@angular/common/http";
import {Brand} from "../../../core/models/device-brand.model";


@Component({
  selector: 'app-marqueDevice',
  templateUrl: './marqueDevice.component.html',
})
export class AppMarqueDeviceComponent {
  columns: any[];
  marque: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;

  private subscriptions: Subscription[] = [];
  constructor(protected devicemarqueService: BranddevisService,private changeDetectorRefs: ChangeDetectorRef) {
    this.columns = [
      {dataField: 'deviceBrand', dataType: "string", caption: 'Marque'},
      {dataField: 'deviceCategory.deviceCategory', dataType: "string", caption: 'Device Catégorie'}
    ];

  }
  ngOnInit(): void {
    this.getBrand(true);
  }
  public getBrand(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.devicemarqueService.getbranddevice().subscribe(
        (response: Brand[]) => {
          this.marque = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.marque, // Initially, there's no data
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
