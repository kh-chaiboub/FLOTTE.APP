
import DataSource from "devextreme/data/data_source";
import {ChangeDetectorRef, Component} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {FormBuilder} from '@angular/forms';
import { HttpErrorResponse, HttpResponse} from '@angular/common/http';

import {map} from 'rxjs/operators';
import {VehicleModel} from '../../../core/models/vehicle-model.model';
import {VehicleTypes} from '../../../core/models/vehicle-types.model';
import {VehicleModelService} from '../../../core/services/vehicle-model.service';
import {VehicleBrands} from '../../../core/models/vehicle-brands.model';
import {BrandService} from '../../../core/services/brand.service';
import {TypeService} from '../../../core/services/typeVl.service';


@Component({
  selector: 'app-modelVl',
  templateUrl: './modelVl.component.html',
})
export class AppModelVlComponent {
  columns: any[];
  typevl: any[];
  dataSource: DataSource;
  isLoading = false;
  collapsed = false;
  private subscriptions: Subscription[] = [];
  defautImageFile: File;
  mouve_D_ImageFile: File;
  mouve_G_ImageFile: File ;
  stop_D_ImageFile: File ;
  stop_G_ImageFile: File ;
  alert_Mission_ImageFile: File ;


  files: any ;
  currentInput: string ;
  imagevl: any;
  constructor(public formBuilder: FormBuilder,
              protected vehicleModelService: VehicleModelService,
              protected vehicleBrandService: BrandService,
              private changeDetectorRefs: ChangeDetectorRef,
              private typeservice: TypeService) {
    this.columns = [
      {dataField: 'modelName', dataType: "string", caption: 'Modele'},
      {dataField: 'vehicleBrands.vehiculeType.vehiculeType', dataType: "string", caption: 'Device ID'}
    ];

  }
  submit: boolean;
  formsubmit: boolean;
  typesubmit: boolean;
  rangesubmit: boolean;
  vehiculeType: any;
  public breadCrumbItems: Array<{}>;
  public vehicleModels: VehicleModel[];
  public editVehicleModel = new VehicleModel();
  deleteVehicleModel?: VehicleModel;
  vehicleBrandsSharedCollection: VehicleBrands[] = [];
  vehicleTypesSharedCollection: VehicleTypes[] = [];
  vehicleModelsSharedCollection: VehicleModel[] = [];
  ngOnInit(): void {
    this.breadCrumbItems = [{label: 'Vehicle '}, {label: 'Modèle', active: true}];

    this.getVehicleModels(true);
    this.getvlmodelImage();

    this.loadRelationshipsOptions();
    this.submit = false;
    this.formsubmit = false;
    this.typesubmit = false;
    this.rangesubmit = false;
  }


  stringify(val: any) {
    return JSON.stringify(val);
  }


  getvlmodelImage() {
    this.vehicleModelService.getDataImage(this.imagevl)
      .pipe(map((res: HttpResponse<any[]>) => {
        return res.body ? res.body : [];
      }))
      .subscribe((results) => {
        console.log(results);
      });
  }

  public getVehicleModels(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.vehicleModelService.getVehicleModel().subscribe(
        (response: VehicleModel[]) => {
          this.vehicleModels = response.map((pathVl) => ({
            id: pathVl.id,
            modelName: pathVl.modelName,
            vehicleBrands: pathVl.vehicleBrands,
          }));
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.vehicleModels, // Initially, there's no data
              key: 'id' // Replace with the primary key field of your Device model
            },
            paginate: true // Enable server-side pagination
          });
          this.isLoading = false;
          this.changeDetectorRefs.detectChanges();
          this.loadRelationshipsOptions();
          this._fetchData(this.vehicleModels);

        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoading = false;
          console.log(JSON.stringify(errorResponse));
        }
      )
    );
  }

  private _fetchData(vehicleModelData: VehicleModel[]) {

    console.log(vehicleModelData);
    this.isLoading = true;
    this.vehicleModels = vehicleModelData;
  }

  protected loadRelationshipsOptions(): void {

    this.typeservice
      .query()
      .pipe(map((res: HttpResponse<VehicleTypes[]>) => {

        return res.body ? res.body : [];
      }))

      .subscribe((vehicleType: VehicleTypes[]) => (this.vehicleTypesSharedCollection = vehicleType

      ));



  }





}
