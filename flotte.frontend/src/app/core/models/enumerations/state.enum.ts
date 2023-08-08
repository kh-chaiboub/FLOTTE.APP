export  enum state  {
  GOOD = 'GOOD',
  BROKEN= 'BROKEN',
  UNDER_REPARATION= 'UNDER_REPARATION',
  REPARED= 'REPARED',
  CAN_NOT_BE_FIXED= 'CAN_NOT_BE_FIXED'
}




export const State2LabelMapping: Record<state, string> = {
  [state.GOOD]: 'RAS',
  [state.BROKEN]: 'CASSÉ',
  [state.UNDER_REPARATION]: 'EN RÉPARATION',
  [state.REPARED]: 'RÉPARÉ',
  [state.CAN_NOT_BE_FIXED]: 'REFORME'
};

