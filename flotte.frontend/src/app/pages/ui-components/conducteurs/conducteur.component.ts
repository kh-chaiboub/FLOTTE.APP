import {ChangeDetectorRef, Component} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";
import {DriverService} from "../../../core/services/driver.service";
import {Driver} from "../../../core/models/driver.model";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-conducteur',
  templateUrl: './conducteur.component.html',
})
export class AppConducteurComponent {
  columns: any[];
  driver: any[];
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
  constructor(protected driverService: DriverService,private changeDetectorRefs: ChangeDetectorRef) {
    this.columns = [
      {dataField: 'person.lastName', dataType: "string", caption: 'Nom'},
      {dataField: 'person.firstName', dataType: "string", caption: 'Prénom'},
      {dataField: 'person.dateOfBirth', dataType: "date", caption: 'Date N'},
      {dataField: 'person.gsm', dataType: "string", caption: 'GSM'},
      {dataField: 'person.ncin', dataType: "string", caption: 'CIN'},
      {dataField: 'categoryPermis', dataType: "string", caption: 'Catégorie Permis'},

    ];

  }
  ngOnInit(): void {
  this.getDriver(true, 0, 40);}

  public getDriver(showNotification: boolean,page:number,size:number): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.driverService.getDriverProjection(page,size).subscribe(
        (response: Driver[]) => {
          this.driver = response;
          console.log( this.driver)
          this.isLoading = false;
          this.changeDetectorRefs.detectChanges();
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.driver,
              key: 'id'
            },
            paginate: true
          });

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

  loadDataIndexChange(e: number) {
    this.pagingOptions.currentPage = e;
    console.log( this.pagingOptions)
    this.getDriver(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
    //this.getLastPositions(true);
  }

  loadDataSizeChange(e: number) {
    this.pagingOptions.pageSize = e;
    this.pagingOptions.currentPage = 0;
    console.log( this.pagingOptions)
    this.getDriver(true,this.pagingOptions.currentPage,this.pagingOptions.pageSize);
  }


}
