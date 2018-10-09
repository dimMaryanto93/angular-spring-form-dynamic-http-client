export interface Field {
  id: string;
  columnName: string;
  labelName: string;
  dataType: DataType;

}

export enum DataType {
  INT, STRING, DATE, DATE_TIME, TEXT, BOOLEAN
}
