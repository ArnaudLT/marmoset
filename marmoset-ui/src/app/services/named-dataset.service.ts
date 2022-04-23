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

  public namedDatasets: MDataset[] = new Array<MDataset>();

  public sqlQueryOutput: SqlQueryOutput = new SqlQueryOutput();

  constructor(private http: HttpClient) { }


  catalog() {
    return this.http
      .get<MDataset[]>("http://localhost:8080/api/v1/datasets/catalog", this.httpOptions)
      //.subscribe(mds => this.namedDatasets = mds);
      .subscribe({
        next: (mds) => {
          console.log('catalog next'); 
          this.namedDatasets = mds;},
        error: (err) => console.error("catalog error", err),
        complete: () => console.info('catalog complete')
      });
  }

  load(datasetImportSettings : DatasetImportSettings) {
    return this.http
      .post<MDataset>("http://localhost:8080/api/v1/datasets/load", datasetImportSettings, this.httpOptions)
      .subscribe({
        next: (mds) => {
          console.log('load next'); 
          this.namedDatasets.push(mds);},
        error: (err) => console.error("load error", err),
        complete: () => console.info('load complete')
      });
  }

  unload(datasetName: DatasetName) {
    return this.http
      .delete<void>("http://localhost:8080/api/v1/datasets/unload", Object.assign(this.httpOptions, {body: datasetName}))
      .subscribe({
        next: (mds) => {
          console.log('unload next');
          this.removeDataset(datasetName.name);},
        error: (err) => console.error("unload error", err),
        complete: () => console.info('unload complete')
      });      
  }

  runQuery(sqlQuery: SqlQuery) {
      return this.http
        .post<SqlQueryOutput>("http://localhost:8080/api/v1/datasets/run-query", sqlQuery, this.httpOptions)
        .subscribe({
          next: (output) => {
            console.log('runQuery next');
            this.sqlQueryOutput = output;},
          error: (err) => console.error("runQuery error", err),
          complete: () => console.info('runQuery complete')
        });;
  }

  // -------------------------------------------------------------
  // -------------------------------------------------------------
  // -------------------------------------------------------------
  private removeDataset(datasetName: string) {
    
    var datasetIndex;
    for (var i=0; i<this.namedDatasets.length; i++) {
      if (this.namedDatasets[i].datasetName.name === datasetName) {
        this.namedDatasets.splice(i, 1);
      }
    }
  }

}
