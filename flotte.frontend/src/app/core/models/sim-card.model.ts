import { IOperator } from './operator.model';

export interface ISimCard {
  id?: string;
  phoneNumber?: number;
  serialNumber?: string;
  operator?: IOperator;
}

export class SimCard implements ISimCard {
  constructor(public id?: string, public phoneNumber?: number, public serialNumber?: string, public operator?: IOperator) {}
}
