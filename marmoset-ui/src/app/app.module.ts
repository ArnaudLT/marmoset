import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MarmosetExplorerComponent } from './marmoset-explorer/marmoset-explorer.component';
import { MarmosetOverviewComponent } from './marmoset-overview/marmoset-overview.component';
import { MarmosetSqlComponent } from './marmoset-sql/marmoset-sql.component';

@NgModule({
  declarations: [
    AppComponent,
    MarmosetExplorerComponent,
    MarmosetOverviewComponent,
    MarmosetSqlComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
