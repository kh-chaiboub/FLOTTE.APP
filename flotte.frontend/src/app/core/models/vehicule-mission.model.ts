import * as dayjs from 'dayjs';
import {Person} from './person.model';
import {FactionMissionModel} from './faction-mission.model';
import {Vehicle} from './vehicle.model';
export class VehiculeMission {
  id?: string;
  missionVehiculeName?: string | null;
  // tslint:disable-next-line:variable-name
  mission_DateD?: dayjs.Dayjs;
  // tslint:disable-next-line:variable-name
  mission_DateF?: dayjs.Dayjs;
  factionMissionList?: FactionMissionModel[] | null;
  vehicule?: Vehicle[] | null ;
  descriptionMission?: string | null;



  constructor() {
    this.id = null;
    this.missionVehiculeName = null;
    this.mission_DateD = null;
    this.mission_DateF = null;
    this.factionMissionList = null;
    this.vehicule = null;
    this.descriptionMission = null;

  }
}
export  function getVehiculeMissionIdentifier(VehiculeMissionModel: VehiculeMission): string | undefined {
  return VehiculeMissionModel.id;
}

