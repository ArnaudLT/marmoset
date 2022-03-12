import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MarmosetExplorerComponent } from './marmoset-explorer/marmoset-explorer.component';

@NgModule({
  declarations: [
    AppComponent,
    MarmosetExplorerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
