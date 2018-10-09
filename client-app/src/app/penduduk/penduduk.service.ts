import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Field} from '../field';

@Injectable({
  providedIn: 'root'
})
export class PendudukService {

  constructor(private _http: HttpClient) {
  }

  getField() {
    return this._http.get<Field[]>(`${environment.baseApi}/penduduk/fields`);
  }

  setField(data: any) {
    return this._http.post(`${environment.baseApi}/penduduk/fields`, data);
  }

}
