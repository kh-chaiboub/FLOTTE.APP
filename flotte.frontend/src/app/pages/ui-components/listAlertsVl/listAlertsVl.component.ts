import {ChangeDetectorRef, Component, enableProdMode} from '@angular/core';
import DataSource from "devextreme/data/data_source";
import {Subscription} from "rxjs";
import {alerte} from "../../../core/models/alerts.model";
import {AlerteService} from "../../../core/services/alerte.service";
import {HttpErrorResponse} from "@angular/common/http";

if (!/localhost/.test(document.location.host)) {
  enableProdMode();
}

@Component({
  selector: 'app-listAlertsVl',
  templateUrl: './listAlertsVl.component.html',
  styleUrls: ['./listAlertsVl.component.scss'],
})
export class AppListAlertsVlComponent {
  columns: any[];
  dataSource: DataSource;

  public alerts: alerte[];
  isLoading = false;
  private subscriptions: Subscription[] = [];
  constructor( protected alerteService: AlerteService,private changeDetectorRefs: ChangeDetectorRef) {
    this.columns = [
      { dataField: 'registrationNumber',dataType:"string", caption: 'registration Number' },
      { dataField: 'vehiculeDesc',dataType:"string", caption: 'vehicule' },
      { dataField: 'vehiculeMaxVitesse', dataType:"number",caption: 'vehicule Max Vitesse' },
      { dataField: 'etatVehicule.vehicleStatus',dataType:"string", caption: 'Etat' },
      { dataField: 'device.deviceID',dataType:"string", caption: 'device ID' },
    ];


  }
  ngOnInit(): void {

    this.getalerte(true);

  }
  private _fetchData({AlerteData}: { AlerteData: any }) {
    this.isLoading = true;
    this.alerts = AlerteData;
  }
  public getalerte(showNotification: boolean): void {
    this.isLoading = true;
    this.subscriptions.push(
      this.alerteService.getalerte().subscribe(
        (response: alerte[]) => {
          this.alerts = response;
          this.isLoading = false;
          this.dataSource = new DataSource({
            store: {
              type: 'array',
              data: this.alerts, // Initially, there's no data
              key: 'alertsID' // Replace with the primary key field of your Device model
            },
            paginate: true // Enable server-side pagination
          });

          // this.loadRelationshipsOptions();
          this.changeDetectorRefs.detectChanges();

          this._fetchData({AlerteData: this.alerts});
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoading = false;
          console.log(JSON.stringify(errorResponse));
        }
      )
    );
  }

}

