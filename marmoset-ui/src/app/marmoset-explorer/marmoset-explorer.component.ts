import { Component, OnInit } from '@angular/core';
import { MDataset, NamedDatasetService } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-explorer',
  templateUrl: './marmoset-explorer.component.html',
  styleUrls: ['./marmoset-explorer.component.css']
})
export class MarmosetExplorerComponent implements OnInit {

  namedDatasets: MDataset[] = [];

  constructor(private namedDatasetService: NamedDatasetService) {}

  ngOnInit(): void {
    this.test();
  }

  test() {

    this.namedDatasetService.test()
      .subscribe(mds => this.namedDatasets = mds)
  }
  
}
