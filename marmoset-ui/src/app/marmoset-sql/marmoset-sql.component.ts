import { Component, OnInit } from '@angular/core';
import { NamedDatasetService, SqlQuery } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-sql',
  templateUrl: './marmoset-sql.component.html',
  styleUrls: ['./marmoset-sql.component.css']
})
export class MarmosetSqlComponent implements OnInit {

  constructor(private namedDatasetService: NamedDatasetService) { }

  ngOnInit(): void {
  }

  runQuery(sqlQuery: SqlQuery) {

    this.namedDatasetService.runQuery(sqlQuery);      
  }

}
