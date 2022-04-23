import { Component, OnInit } from '@angular/core';
import { NamedDatasetService, SqlQueryOutput } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-overview',
  templateUrl: './marmoset-overview.component.html',
  styleUrls: ['./marmoset-overview.component.css']
})
export class MarmosetOverviewComponent implements OnInit {  

  constructor(public namedDatasetService: NamedDatasetService) { }

  ngOnInit(): void {
  }

}
