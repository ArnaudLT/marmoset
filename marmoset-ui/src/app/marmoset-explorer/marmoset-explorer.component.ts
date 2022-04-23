import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { DatasetImportSettings, DatasetName, MDataset, NamedDatasetService, SqlQuery, SqlQueryOutput } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-explorer',
  templateUrl: './marmoset-explorer.component.html',
  styleUrls: ['./marmoset-explorer.component.css']
})
export class MarmosetExplorerComponent implements OnInit {
 

  constructor(public namedDatasetService: NamedDatasetService) {}

  ngOnInit(): void {

    this.catalog();
  }

  catalog() {

    this.namedDatasetService.catalog();
  }

  load(datasetImportSettings: DatasetImportSettings) {

    this.namedDatasetService.load(datasetImportSettings);
  }

  unload(datasetName: DatasetName) {

    this.namedDatasetService.unload(datasetName);
  }  
  
}
