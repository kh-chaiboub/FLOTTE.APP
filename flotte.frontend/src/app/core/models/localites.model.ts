export interface ILocalites {

  id?: string;
  province?: string;
  localite?: string;
  x?: number;
  y?: number;
  niveau?: number;
  slink?: number;
  code?: number;
  route?: string;
  pk?: number;
  xText?: number;
  yText?: number;
  dep?: number;
  ar?: number;
  process?: number;
  mslinkTrajet?: number;
  pkDep?: number;
  pkAr?: number;
  deport?: number;
  codeDeport?: string;
  latitude?: number;
  longitude?: number;
  region?: string,
  cie?: string,
  bt?: string,

}

export class Localites implements ILocalites {
  constructor(
    public id?: string,
    public province?: string,
    public localite?: string,
    public x?: number,
    public y?: number,
    public niveau?: number,
    public slink?: number,
    public code?: number,
    public route?: string,
    public pk?: number,
    public xText?: number,
    public yText?: number,
    public dep?: number,
    public ar?: number,
    public process?: number,
    public mslinkTrajet?: number,
    public pkDep?: number,
    public pkAr?: number,
    public deport?: number,
    public codeDeport?: string,
    public latitude?: number,
    public longitude?: number,
    public region?: string,
    public cie?: string,
    public bt?: string,
  ) {}
}
