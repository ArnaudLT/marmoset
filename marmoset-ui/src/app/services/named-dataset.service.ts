import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';


export interface Catalog {

  datasetNames: DatasetName[];
}

export interface MDataset {

  datasetName: DatasetName;
  schema: Schema;
}

export interface DatasetName {

  name: string;
}

export class Schema {

  fields: Set<Field>;

  constructor() {
    this.fields = new Set<Field>();
  }
}

export class Field {

  name: string;
  type: string;
  innerFields: Set<Field>;

  constructor() {
    this.name = "";
    this.type = "";
    this.innerFields = new Set<Field>();
  }
}

export interface DatasetImportSettings {

  uri: string;
  basePath: string;
  requestedName: string

}

export class SqlQueryOutput {

  sqlQuery: SqlQuery;
  schema: Schema;
  outputRows: OutputRows;

  constructor() {
    this.sqlQuery = new SqlQuery();
    this.schema = new Schema();
    this.outputRows = new OutputRows();
  }
  
}

export class OutputRows {

  rows: Array<Map<string, string>>;

  constructor() {
    this.rows = Array<Map<string,string>>();
  }
}

export class SqlQuery {

  query: string;

  constructor() {
    this.query = "";
  }
}



@Injectable()
export class NamedDatasetService {

  private httpOptions = {headers: new HttpHeaders().set('Content-Type', 'application/json')};  

  constructor(private http: HttpClient) { }

  catalog() : Observable<MDataset[]> {
    return this.http.get<MDataset[]>(
      "http://localhost:8080/api/v1/datasets/catalog", 
      this.httpOptions);
  }

  load(datasetImportSettings : DatasetImportSettings) : Observable<MDataset> {
    return this.http.post<MDataset>(
      "http://localhost:8080/api/v1/datasets/load", 
      datasetImportSettings, 
      this.httpOptions);
  }

  unload(datasetName: DatasetName) : Observable<void> {
    return this.http.delete<void>("http://localhost:8080/api/v1/datasets/unload",
      Object.assign(this.httpOptions, {body: datasetName}));      
  }

  runQuery(sqlQuery: SqlQuery) : Observable<SqlQueryOutput> {
      return this.http.post<SqlQueryOutput>(
        "http://localhost:8080/api/v1/datasets/run-query",
        sqlQuery,
        this.httpOptions);
  }

}
