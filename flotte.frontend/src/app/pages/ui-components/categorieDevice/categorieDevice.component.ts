import { Component} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";
import {CategoryService} from "../../../core/services/category.service";
import {DeviceCategory} from "../../../core/models/device-category.model";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-categorieDevice',
  templateUrl: './categorieDevice.component.html',
})
export class AppCategorieDeviceComponent {
  columns: any[];
  categorie: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];
  constructor(protected deviceCategoryService: CategoryService) {
    this.columns = [
      {dataField: 'deviceCategory', dataType: "string", caption: 'Categorie'}
    ];

  }
  ngOnInit(): void {
    this.getCategory(true);
  }
  public getCategory(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.deviceCategoryService.getCategories().subscribe(
        (response: DeviceCategory[]) => {
          this.categorie = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.categorie, // Initially, there's no data
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
