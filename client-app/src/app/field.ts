export interface Field {
  columnName: string;
  labelName: string;
  dataType: DataType;

}

export enum DataType {
  INT, STRING, DATE, DATE_TIME, TEXT, BOOLEAN
}
