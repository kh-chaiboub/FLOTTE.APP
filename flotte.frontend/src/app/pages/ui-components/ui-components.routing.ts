import { Routes } from '@angular/router';

// ui
import {AppFlotteMapComponent} from './flotteMap/flotteMap.component';
import { AppListAlertsVlComponent} from './listAlertsVl/listAlertsVl.component';
import {AppLastPositionComponent} from './lastPosition/lastPosition.component';
import {AppListDeviceComponent} from './listDevice/listDevice.component';
import { AppTooltipsComponent } from './tooltips/tooltips.component';
import {AppVehicleComponent} from "./vehicle/vehicle.component";
import {AppConducteurComponent} from "./conducteurs/conducteur.component";
import {AppAlertComponent} from "./alerts/alert.component";
import {AppTypeVlComponent} from "./typeVl/typeVl.component";
import {AppMarqueVlComponent} from "./marqueVl/marqueVl.component";
import {AppModelVlComponent} from "./modelVl/modelVl.component";
import {AppCategorieDeviceComponent} from "./categorieDevice/categorieDevice.component";
import {AppMarqueDeviceComponent} from "./marqueDevice/marqueDevice.component";
import {AppModelDeviceComponent} from "./modelDevice/modelDevice.component";
import {AppRefOrganComponent} from "./refOrgans/refOrgan.component";

export const UiComponentsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'flotteMap',
        component: AppFlotteMapComponent,
      },
      {
        path: 'vehicle',
        component: AppVehicleComponent,
      },
      {
        path: 'listAlertsVl',
        component: AppListAlertsVlComponent,
      },
      {
        path: 'lastPosition',
        component: AppLastPositionComponent,
      },
      {
        path: 'listDevice',
        component: AppListDeviceComponent,
      },
      {
        path: 'conducteurs',
        component: AppConducteurComponent,
      },
      {
        path: 'alerts',
        component: AppAlertComponent,
      },
      {
        path: 'typeVl',
        component: AppTypeVlComponent,
      },
      {
        path: 'marqueVl',
        component: AppMarqueVlComponent,
      },
      {
        path: 'modelVl',
        component: AppModelVlComponent,
      },
      {
        path: 'categorieDevice',
        component: AppCategorieDeviceComponent,
      },
      {
        path: 'marqueDevice',
        component: AppMarqueDeviceComponent,
      },
      {
        path: 'modelDevice',
        component: AppModelDeviceComponent,
      },
      {
        path: 'tooltips',
        component: AppTooltipsComponent,
      },
      {
        path: 'refOrgans',
        component: AppRefOrganComponent,
      },
    ],
  },
];
