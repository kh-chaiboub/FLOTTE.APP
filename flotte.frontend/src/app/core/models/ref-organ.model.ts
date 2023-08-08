

export interface IRefOrgan {
  id?: string;
  refOrganName?: string;
  prefOrgan?: string;
}

export class RefOrgan implements IRefOrgan {
  constructor(public id?: string, public refOrganName?: string, public prefOrgan?: string) {}
}
export function getRefOrganIdentifier(refOrgan: RefOrgan): string | undefined {
  return refOrgan.id;
}
