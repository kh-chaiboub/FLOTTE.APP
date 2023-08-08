
export enum Grade {
  MDL_GENDARME='MDL_GENDARME',
  MDL_CHEF = 'MDL_CHEF',
  ADJUDENT = 'ADJUDENT',
  ADJT_CHEF = 'ADJT_CHEF'

}


export const GradeVl2LabelMapping: Record<Grade, string> = {
  [Grade.MDL_GENDARME]: 'MDL_GENDARME',
  [Grade.MDL_CHEF]: 'MDL_CHEF',
  [Grade.ADJUDENT]: 'ADJUDENT',
  [Grade.ADJT_CHEF]: 'ADJT_CHEF'
};

