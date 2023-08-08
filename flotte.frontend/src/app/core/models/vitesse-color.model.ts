export interface IVitesseColor {
  id?: string;
  vitesse1?: number;
  vitesse2?: number;
  couleur?: string;
}

export class VitesseColor implements IVitesseColor {
  constructor(public id?: string, public vitesse1?: number, public vitesse2?: number, public couleur?: string) {}
}
