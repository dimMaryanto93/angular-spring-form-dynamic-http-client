import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AddPendudukComponent} from './penduduk/add-penduduk/add-penduduk.component';
import {ListPendudukComponent} from './penduduk/list-penduduk/list-penduduk.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {UpdatePendudukComponent} from './penduduk/update-penduduk/update-penduduk.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {AppRoutingModule} from './app-routing.module';
import { DefaultLayoutComponent } from './default-layout/default-layout.component';

@NgModule({
  declarations: [
    AppComponent,
    ListPendudukComponent,
    AddPendudukComponent,
    UpdatePendudukComponent,
    PageNotFoundComponent,
    DefaultLayoutComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
