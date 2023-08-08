import {Component} from '@angular/core';

import {Subscription} from "rxjs";
import DataSource from "devextreme/data/data_source";


@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
})
export class AppAlertComponent {
  dataSource: DataSource;
  columns: any[];

  private subscriptions: Subscription[] = [];
  constructor() {
    this.columns = [
      {dataField: 'registrationNumber', dataType: "string", caption: 'Matricule'},
      {dataField: 'device.deviceID', dataType: "string", caption: 'Device ID'},
      {dataField: 'lastPosition.deviceTime', dataType: "date", caption: 'Date Position'},
      {dataField: 'lastPosition.speed', dataType: "number", caption: 'Vitesse km/h'},
      {dataField: 'lastPosition.localite.localite', dataType: "string", caption: 'Localite'},
    ];

  }


}
