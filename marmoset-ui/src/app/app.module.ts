import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MarmosetExplorerComponent } from './marmoset-explorer/marmoset-explorer.component';
import { MarmosetOverviewComponent } from './marmoset-overview/marmoset-overview.component';
import { MarmosetSqlComponent } from './marmoset-sql/marmoset-sql.component';
import { NamedDatasetService } from './services/named-dataset.service';

@NgModule({
  declarations: [
    AppComponent,
    MarmosetExplorerComponent,
    MarmosetOverviewComponent,
    MarmosetSqlComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [NamedDatasetService],
  bootstrap: [AppComponent]
})
export class AppModule { }
