import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {PendudukService} from '../penduduk.service';
import {DataType, Field} from '../../field';

@Component({
  selector: 'app-add-penduduk',
  templateUrl: './add-penduduk.component.html',
  styleUrls: ['./add-penduduk.component.css']
})
export class AddPendudukComponent implements OnInit {

  formGroup: FormGroup;
  formGroups: FormArray;
  fields: Field[] = [];
  dataType = DataType;

  constructor(
    private _pendudukService: PendudukService,
    private _formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.formGroups = new FormArray([]);
    this._pendudukService.getField().subscribe(data => {
      this.fields = data;
      console.log(`size of fields is ${this.fields.length}`);
      for (let field of this.fields) {
        const formGroup = this._formBuilder.group(
          {
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
