import {ChangeDetectorRef, Component} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";
import {VehicleTypes} from "../../../core/models/vehicle-types.model";
import {HttpErrorResponse} from "@angular/common/http";
import {BrandService} from "../../../core/services/brand.service";
import {VehicleBrands} from "../../../core/models/vehicle-brands.model";


@Component({
  selector: 'app-marqueVl',
  templateUrl: './marqueVl.component.html',
})
export class AppMarqueVlComponent {
  columns: any[];
  vehicleBrands: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];

  constructor(
              protected brandService: BrandService,
              private changeDetectorRefs: ChangeDetectorRef) {
    this.columns = [
      {dataField: 'vehicleBrand', dataType: "string", caption: 'Marque Moyen'},
      {dataField: 'vehiculeType.vehiculeType', dataType: "string", caption: 'Type Moyen'}
    ];

  }
  ngOnInit(): void {
    this.getBrands(true);
  }
  public getBrands(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.brandService.getBrands().subscribe(
        (response: VehicleBrands[]) => {
          this.vehicleBrands = response;
          this.isLoading = false;
          this.changeDetectorRefs.detectChanges();
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.vehicleBrands, // Initially, there's no data
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
