import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { DatasetImportSettings, DatasetName, MDataset, NamedDatasetService } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-explorer',
  templateUrl: './marmoset-explorer.component.html',
  styleUrls: ['./marmoset-explorer.component.css']
})
export class MarmosetExplorerComponent implements OnInit {

  namedDatasets: MDataset[] = [];

  constructor(private namedDatasetService: NamedDatasetService) {}

  ngOnInit(): void {}

  test() {

    this.namedDatasetService.test()
      .subscribe(mds => this.namedDatasets = mds)
  }

  catalog() {

    this.namedDatasetService.catalog()
      .subscribe(mds => this.namedDatasets = mds)
  }

  load(datasetImportSettings: DatasetImportSettings) {

    this.namedDatasetService.load(datasetImportSettings)
      .subscribe({
        next: (mds) => console.log('load next'),
        error: (err) => console.error("load error", err),
        complete: () => console.info('load complete')
      });
  }

  unload(datasetName: DatasetName) {

    this.namedDatasetService.unload(datasetName)
      .subscribe({
        next: (mds) => console.log('unload next'),
        error: (err) => console.error("unload error", err),
        complete: () => console.info('unload complete')
      });
  }
  
}
