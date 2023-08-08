export class alerte {
    id?: string;
    vl?: string | null;
    Organisation?: string | null;
    Date?: Date| null;
    Description ?: string | null;
    Responsable ?: string | null;

  // tslint:disable-next-line:max-line-length
    constructor(id?: string, vl?: string | null, Organisation?: string | null, Date?: Date | null, Description?: string|null, Responsable?: string|null) {
      this.id = id;
      this.vl = vl;
      this.Organisation = Organisation;
      this.Date = Date;
      this.Description = Description;
      this.Responsable = Responsable;
    }
  }

export function getAlerteIdentifier(alerte: alerte): string | undefined {
    return alerte.id;
  }
