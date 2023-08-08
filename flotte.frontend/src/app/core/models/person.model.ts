

export class Person {
  id?: null;
  firstName?: null;
  lastName?: null;
  dateOfBirth?: null;
  ncin?: null;
  fonction?: null;
  grade?: null;
  mle?: null;
  gsm?: null;
  refOrgan?:null;
  prefOrgan?: null;

  constructor() {
    this.id = null;
    this.firstName = null;
    this.lastName = null;
    this.dateOfBirth = null;
    this.ncin = null;
    this.fonction = null;
    this.grade = null;
    this.mle = null;
    this.gsm = null;
    this.refOrgan=null;
    this.prefOrgan=null;
  }
}



export function getUserIdentifier(person: Person): null | undefined {
  return person.id;
}
export interface SearchResult {
  tables: Person[];
  total: number;
}
