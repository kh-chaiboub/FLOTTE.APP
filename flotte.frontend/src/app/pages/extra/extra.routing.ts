import { Routes } from '@angular/router';


// pages
import { AppSamplePageComponent } from './sample-page/sample-page.component';

export const ExtraRoutes: Routes = [
  {
    path: '',
    children: [
      /*{
        path: 'vehicle',
        component: AppVehicleComponent,
      },*/
      {
        path: 'sample-page',
        component: AppSamplePageComponent,
      },
    ],
  },
];
