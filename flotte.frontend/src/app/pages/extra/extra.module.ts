import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

// icons
import { TablerIconsModule } from 'angular-tabler-icons';
import * as TablerIcons from 'angular-tabler-icons/icons';

import { ExtraRoutes } from './extra.routing';
import { AppSamplePageComponent } from './sample-page/sample-page.component';
import {DxDataGridModule} from "devextreme-angular";
import {AppModule} from "../../app.module";


@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(ExtraRoutes),
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        TablerIconsModule.pick(TablerIcons),
        DxDataGridModule,
        AppModule,

    ],
  declarations: [
    AppSamplePageComponent
  ],
})
export class ExtraModule {}
