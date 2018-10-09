import {Field} from '../field';

export class PendudukDTO {
  id: string;
  namaLenkap: string;
  jenisKelamin: string;
  extended: Field[] = [];
}
