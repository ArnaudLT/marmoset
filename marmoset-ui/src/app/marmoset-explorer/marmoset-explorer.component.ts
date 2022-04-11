import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { DatasetImportSettings, DatasetName, MDataset, NamedDatasetService, SqlQuery } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-explorer',
  templateUrl: './marmoset-explorer.component.html',
  styleUrls: ['./marmoset-explorer.component.css']
})
export class MarmosetExplorerComponent implements OnInit {

  namedDatasets: MDataset[] = [];

  constructor(private namedDatasetService: NamedDatasetService) {}

  ngOnInit(): void {

    this.catalog();
  }

  catalog() {

    this.namedDatasetService.catalog()
      .subscribe(mds => this.namedDatasets = mds)
  }

  load(datasetImportSettings: DatasetImportSettings) {

    this.namedDatasetService.load(datasetImportSettings)
      .subscribe({
        next: (mds) => {
          console.log('load next'); 
          this.namedDatasets.push(mds);},
        error: (err) => console.error("load error", err),
        complete: () => console.info('load complete')
      });
  }

  unload(datasetName: DatasetName) {

    this.namedDatasetService.unload(datasetName)
      .subscribe({
        next: (mds) => {
          console.log('unload next');
          this.removeDataset(datasetName.name);},
        error: (err) => console.error("unload error", err),
        complete: () => console.info('unload complete')
      });
  }

  runQuery(sqlQuery: SqlQuery) {

    this.namedDatasetService.runQuery(sqlQuery)
      .subscribe({
        next: (output) => console.log('runQuery next'),
        error: (err) => console.error("runQuery error", err),
        complete: () => console.info('runQuery complete')
      });
  }

  private removeDataset(datasetName: string) {
    
    var datasetIndex;
    for (var i=0; i<this.namedDatasets.length; i++) {
      if (this.namedDatasets[i].datasetName.name === datasetName) {
        this.namedDatasets.splice(i, 1);
      }
    }
  }
  
}
