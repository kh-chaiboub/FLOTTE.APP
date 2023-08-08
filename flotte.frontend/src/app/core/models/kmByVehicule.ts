import {Vehicle} from './vehicle.model';

export class KmByVehicule{
  totalDistance?:number | null;
  vehicule?:Vehicle | null;
  constructor() {
    this.totalDistance = null;
    this.vehicule = null;
  }
}
