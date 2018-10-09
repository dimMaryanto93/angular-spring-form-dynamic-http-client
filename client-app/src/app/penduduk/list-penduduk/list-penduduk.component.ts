import {Component, OnInit} from '@angular/core';
import {PendudukService} from '../penduduk.service';
import {PendudukDTO} from '../penduduk.dto';

@Component({
  selector: 'app-list-penduduk',
  templateUrl: './list-penduduk.component.html',
  styleUrls: ['./list-penduduk.component.css']
})
export class ListPendudukComponent implements OnInit {

  datas: PendudukDTO[] = [];

  constructor(private _pendudukService: PendudukService) {
  }

  ngOnInit() {
    this._pendudukService.list().subscribe(data => {
      this.datas = data;
    }, error => console.log(error));
  }

}
