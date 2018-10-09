export interface Field {
  id: string;
  columnName: string;
  labelName: string;
  masterId: any;
  dataType: DataType;
  value: any;
}

export enum DataType {
  INT, STRING, DATE, DATE_TIME, TEXT, BOOLEAN
}
