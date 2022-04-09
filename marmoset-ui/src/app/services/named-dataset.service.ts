import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';


export interface MDataset {

  datasetName : MDatasetName;
  schema : Schema;
}

export interface MDatasetName {

  name : string;
}

export interface Schema {

  fields : Set<Field>;
}

export interface Field {

  name : string;
  type : string;
  innerFields : Set<Field>;
}


@Injectable()
export class NamedDatasetService {

  private httpOptions = {headers: new HttpHeaders().set( 'Content-Type', 'application/json' )};  

  constructor(private http: HttpClient) { }

  test() : Observable<MDataset[]> {
    return this.http.get<MDataset[]>("http://localhost:8080/api/v1/datasets/test", this.httpOptions);
  }

}
