import { Routes } from '@angular/router';
import { AppDashboardComponent } from './dashboard/dashboard.component';
import {AuthGuard} from "../guards/security.guard";

export const PagesRoutes: Routes = [
  {
    path: '',
    component: AppDashboardComponent,
    data: {
      title: 'Starter Page',
    },
  },
];
