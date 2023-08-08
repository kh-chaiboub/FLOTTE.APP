
import {Person} from '../models/person.model';


export class Driver {
  id?: null;
  nbrmilitaire?: string;
  npermis?: string;
  categoryPermis?: string | null;
  mle?: number | null;
  person?: Person | null;



  constructor() {
    this.id = null;
    this.nbrmilitaire = '';
    this.npermis = '';
    this.categoryPermis = '';
    this.mle = null;
    this.person = null;


  }
}


export function getDriverIdentifier(driver: Driver): null | undefined {
  return driver.id;
}
