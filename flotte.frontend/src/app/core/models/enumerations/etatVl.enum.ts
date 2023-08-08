export enum etatVlEnum {
  GPS_EN_PANNE = 'GPS_EN_PANNE',
  RAS = 'RAS',
  VL_EN_PANNE = ' VL_EN_PANNE',
  ACCIDENTEE = ' ACCIDENTEE',
  REPARATION = 'REPARATION',
  BATTERIE_VL_ENDOMMAGE = 'BATTERIE_VL_ENDOMMAGE',

}


export const EtatVl2LabelMapping: Record<etatVlEnum, string> = {
  [etatVlEnum.GPS_EN_PANNE]: 'GPS en Panne',
  [etatVlEnum.RAS]: 'RAS',
  [etatVlEnum.VL_EN_PANNE]: 'Panne',
  [etatVlEnum.ACCIDENTEE]: 'Accidentee',
  [etatVlEnum.REPARATION]: 'Reparation',
  [etatVlEnum.BATTERIE_VL_ENDOMMAGE]: 'Batterie Endommagée'
};

