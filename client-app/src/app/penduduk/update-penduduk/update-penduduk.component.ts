import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {Field} from '../../field';
import {PendudukService} from '../penduduk.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PendudukDTO} from '../penduduk.dto';
import * as moment from 'moment';

@Component({
  selector: 'app-update-penduduk',
  templateUrl: './update-penduduk.component.html',
  styleUrls: ['./update-penduduk.component.css']
})
export class UpdatePendudukComponent implements OnInit, AfterViewInit {

  id: string;
  formGroup: FormGroup;
  formGroups: FormArray;
  dto: PendudukDTO;
  private fields: Field[];

  constructor(
    private _pendudukService: PendudukService,
    private _formBuilder: FormBuilder,
    private _activatedRoute: ActivatedRoute,
    private _route: Router) {
  }

  ngOnInit() {
    this.formGroups = new FormArray([]);
    this._pendudukService.getFields().subscribe(data => {
      this.fields = data.extended;
      console.log(`size of fields is ${this.fields.length}`);
      for (let field of this.fields) {
        const formGroup = this._formBuilder.group(
          {
            'id': this._formBuilder.control(''),
            'columnName': this._formBuilder.control(field.columnName),
            'dataType': this._formBuilder.control(field.dataType),
            'masterId': this._formBuilder.control(''),
            'value': this._formBuilder.control(''),
          }
        );
        this.formGroups.push(formGroup);
      }
    }, error => {
      console.log(error);
    });

    this.formGroup = new FormGroup({
      'id': this._formBuilder.control(''),
      'nik': this._formBuilder.control(''),
      'namaLengkap': this._formBuilder.control(''),
      'jenisKelamin': this._formBuilder.control(''),
      'extended': this.formGroups
    });
  }

  get extendsForms() {
    return this.formGroup.get('extended') as FormArray;
  }

  submited($event) {
    // console.log(this.formGroup.value);
    // this._pendudukService.save(this.formGroup.value).subscribe(data => {
    //   console.log(data);
    // }, error => console.log(error));
  }

  ngAfterViewInit(): void {
    this._activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this._pendudukService.getFieldsById(this.id).subscribe(response => {
        if (response.status === 204) {
          this._route.navigate(['/form']);
        }
        this.dto = response.body;

        this.formGroup.patchValue({
          'id': this.dto.id,
          'nik': this.dto.nik,
          'namaLengkap': this.dto.namaLengkap,
          'jenisKelamin': this.dto.jenisKelamin
        });

        for (let extended of this.dto.extended) {
          const indexOf = this.fields.findIndex(element => element.columnName === extended.columnName);
          if (indexOf >= 0) {
            let isDateTime = extended.dataType.toString() == 'DATE_TIME';
            if (isDateTime) {
              extended.value = extended.value = moment(extended.value).format('YYYY-MM-DD HH:mm:ss');
            }

            isDateTime = extended.dataType.toString() == 'DATE';
            if (isDateTime) {
              extended.value = extended.value = moment(extended.value).format('YYYY-MM-DD');
            }

            const groupControlAt = this.extendsForms.at(indexOf) as FormGroup;
            groupControlAt.patchValue(extended);
          }
        }
      });
    }, error => console.log(error));
  }
}
