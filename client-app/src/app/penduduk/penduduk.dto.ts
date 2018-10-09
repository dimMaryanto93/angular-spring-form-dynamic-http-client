import {Field} from '../field';

export class PendudukDTO {
  id: string;
  nik: string;
  namaLengkap: string;
  jenisKelamin: string;
  extended: Field[] = [];
}
