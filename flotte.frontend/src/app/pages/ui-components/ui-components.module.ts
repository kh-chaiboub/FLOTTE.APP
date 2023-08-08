import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

// icons
import { TablerIconsModule } from 'angular-tabler-icons';
import * as TablerIcons from 'angular-tabler-icons/icons';

import { UiComponentsRoutes } from './ui-components.routing';

// ui components
import { AppFlotteMapComponent } from './flotteMap/flotteMap.component';
import { AppListAlertsVlComponent } from './listAlertsVl/listAlertsVl.component';
import {AppLastPositionComponent} from './lastPosition/lastPosition.component';
import { AppListDeviceComponent } from './listDevice/listDevice.component';
import { AppTooltipsComponent } from './tooltips/tooltips.component';
import { MatNativeDateModule } from '@angular/material/core';
import {DxBulletModule, DxDataGridModule, DxTreeListModule} from "devextreme-angular";
import {AppVehicleComponent} from "./vehicle/vehicle.component";
import {AppDataGridComponent} from "./dataGrid/dataGrid.component";
import {PopupIconMarkerComponent} from "./popup-icon-marker/popup-icon-marker.component";
import {TreeVehiculeMapComponent} from "./tree-vehicule-map/tree-vehicule-map.component";
import {TreeVehiculeTabMapComponent} from "./tree-vehicule-tab-map/tree-vehicule-tab-map.component";
import {AppConducteurComponent} from "./conducteurs/conducteur.component";
import {AppAlertComponent} from "./alerts/alert.component";
import {AppTypeVlComponent} from "./typeVl/typeVl.component";
import {AppMarqueVlComponent} from "./marqueVl/marqueVl.component";
import {AppModelVlComponent} from "./modelVl/modelVl.component";
import {AppCategorieDeviceComponent} from "./categorieDevice/categorieDevice.component";
import {AppMarqueDeviceComponent} from "./marqueDevice/marqueDevice.component";
import {AppModelDeviceComponent} from "./modelDevice/modelDevice.component";
import {AppRefOrganComponent} from "./refOrgans/refOrgan.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UiComponentsRoutes),
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TablerIconsModule.pick(TablerIcons),
    MatNativeDateModule,
    DxDataGridModule,
    DxBulletModule,
    DxTreeListModule
  ],
  declarations: [
    AppFlotteMapComponent,
    AppVehicleComponent,
    AppListAlertsVlComponent,
    AppLastPositionComponent,
    AppListDeviceComponent,
    AppTooltipsComponent,
    AppDataGridComponent,
    PopupIconMarkerComponent,
    TreeVehiculeMapComponent,
    TreeVehiculeTabMapComponent,
    AppConducteurComponent,
    AppAlertComponent,
    AppTypeVlComponent,
    AppMarqueVlComponent,
    AppModelVlComponent,
    AppCategorieDeviceComponent,
    AppMarqueDeviceComponent,
    AppModelDeviceComponent,
    AppRefOrganComponent
  ],
})
export class UicomponentsModule {}
