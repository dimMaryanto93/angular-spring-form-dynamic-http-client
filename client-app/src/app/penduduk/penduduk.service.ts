import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {PendudukDTO} from './penduduk.dto';

@Injectable({
  providedIn: 'root'
})
export class PendudukService {

  constructor(private _http: HttpClient) {
  }

  getField() {
    return this._http.get<PendudukDTO>(`${environment.baseApi}/penduduk/fields`);
  }

  setField(data: any) {
    return this._http.post(`${environment.baseApi}/penduduk/fields`, data);
  }

}
