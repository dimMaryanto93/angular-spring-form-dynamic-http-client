import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {Field} from '../../field';
import {PendudukService} from '../penduduk.service';

@Component({
  selector: 'app-update-penduduk',
  templateUrl: './update-penduduk.component.html',
  styleUrls: ['./update-penduduk.component.css']
})
export class UpdatePendudukComponent implements OnInit {

  formGroup: FormGroup;
  formGroups: FormArray;
  fields: Field[] = [];

  constructor(
    private _pendudukService: PendudukService,
    private _formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.formGroups = new FormArray([]);
    this._pendudukService.getField().subscribe(data => {
      this.fields = data.extended;
      console.log(`size of fields is ${this.fields.length}`);
      for (let field of this.fields) {
        const formGroup = this._formBuilder.group(
          {
            'id': this._formBuilder.control(field.id),
            'columnName': this._formBuilder.control(field.columnName),
            'dataType': this._formBuilder.control(field.dataType),
            'value': this._formBuilder.control(''),
          }
        );
        this.formGroups.push(formGroup);
      }
    }, error => {
      console.log(error);
    });
    this.formGroup = new FormGroup({'extended': this.formGroups});
    console.log(this.formGroup);
  }

  get extendsForms() {
    return this.formGroup.get('extended') as FormArray;
  }

  submited($event) {
    console.log(this.extendsForms.value);
    this._pendudukService.setField(this.extendsForms.value).subscribe(data => {
      console.log(data);
    }, error => console.log(error));
  }

}
