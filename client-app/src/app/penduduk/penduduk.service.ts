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

  list() {
    return this._http.get<PendudukDTO[]>(`${environment.baseApi}/penduduk/list`);
  }

  getFieldsById(id: string) {
    return this._http.get<PendudukDTO>(`${environment.baseApi}/penduduk/${id}/findById`);
  }


  getFields() {
    return this._http.get<PendudukDTO>(`${environment.baseApi}/penduduk/new`);
  }

  save(data: any) {
    return this._http.post(`${environment.baseApi}/penduduk/save`, data);
  }

}
