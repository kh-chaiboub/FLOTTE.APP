import {Component, enableProdMode, Input} from '@angular/core';
import { DxDataGridModule } from 'devextreme-angular';
import * as AspNetData from 'devextreme-aspnet-data-nojquery';

import {ThemePalette} from '@angular/material/core';
import DataSource from "devextreme/data/data_source";
if (!/localhost/.test(document.location.host)) {
  enableProdMode();
}

@Component({
  selector: 'app-dataGrid',
  templateUrl: './dataGrid.component.html',
  styleUrls: ['./dataGrid.component.scss'],
})
export class AppDataGridComponent {
  @Input() dataSource: DataSource;
  @Input() columns: any[] ;
  customersData: any;
  collapsed = false;

  shippersData: any;

  url: string;


  masterDetailDataSource: any;


  readonly allowedPageSizes = [5, 10, 'all'];
  readonly displayModes = [{ text: "Display Mode 'full'", value: 'full' }, { text: "Display Mode 'compact'", value: 'compact' }];
  displayMode = 'full';
  showPageSizeSelector = true;
  showInfo = true;
  showNavButtons = true;

  constructor() {



  }


  contentReady = (e: any) => {
    if (!this.collapsed) {
      this.collapsed = true;
      e.component.expandRow(['EnviroCare']);
    }
  }

}

