import { Component, OnInit } from '@angular/core';
import { NamedDatasetService } from '../services/named-dataset.service';

@Component({
  selector: 'app-marmoset-overview',
  templateUrl: './marmoset-overview.component.html',
  styleUrls: ['./marmoset-overview.component.css']
})
export class MarmosetOverviewComponent implements OnInit {

  constructor(private namedDatasetService: NamedDatasetService) { }

  ngOnInit(): void {
  }

  test() {
    this.namedDatasetService.test()
      .subscribe(y => console.log(y))
  }

}
