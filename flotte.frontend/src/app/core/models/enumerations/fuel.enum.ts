export enum fuelEnum {
  DIESEL = 'Diesel',
  ESSENCE = 'Essence',
  HYBRIDE = 'Hybride',
  ELECTRIQUE = 'Electrique',


}


export const FuelVl2LabelMapping: Record<fuelEnum, string> = {
  [fuelEnum.DIESEL]: 'Diesel',
  [fuelEnum.ESSENCE]: 'Essence',
  [fuelEnum.HYBRIDE]: 'Hybride',
  [fuelEnum.ELECTRIQUE]: 'Electrique'
};
