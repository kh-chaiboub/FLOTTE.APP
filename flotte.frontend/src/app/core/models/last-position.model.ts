import { Moment } from 'moment';
import { ILocalites } from './localites.model';
import { IVitesseColor } from './vitesse-color.model';

export interface ILastPosition {
  id?: string;
  positionID?: number;
  msLink?: number;
  accuracy?: number;
  address?: string;
  course?: number;
  speed?: number;
  direction?: number;
  altitude?: number;
  latitude?: number;
  longitude?: number;
  valid?: boolean;
  outdated?: boolean;
  crationDate?: Moment;
  fixTime?: Moment;
  deviceTime?: Moment;
  serverTime?: Moment;
  protocol?: string;
  mcc?: number;
  mnc?: number;
  lac?: number;
  cid?: number;
  rssi?: number;
  //device?: IDevice;
  deviceID?: string;
  localites?: ILocalites;
  vitesseColor?: IVitesseColor;
}

export class LastPosition implements ILastPosition {
  constructor(
    public id?: string,
    public positionID?: number,
    public msLink?: number,
    public accuracy?: number,
    public address?: string,
    public course?: number,
    public speed?: number,
    public direction?: number,
    public altitude?: number,
    public latitude?: number,
    public longitude?: number,
    public valid?: boolean,
    public outdated?: boolean,
    public crationDate?: Moment,
    public fixTime?: Moment,
    public deviceTime?: Moment,
    public serverTime?: Moment,
    public protocol?: string,
    public mcc?: number,
    public mnc?: number,
    public lac?: number,
    public cid?: number,
    public rssi?: number,
   // public device?: IDevice,
    public deviceID?:string,
    public localite?: ILocalites,
    public vitesseColor?: IVitesseColor
  ) {
    this.valid = this.valid || false;
    this.outdated = this.outdated || false;
  }
}
