import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ListPendudukComponent} from './penduduk/list-penduduk/list-penduduk.component';
import {UpdatePendudukComponent} from './penduduk/update-penduduk/update-penduduk.component';
import {AddPendudukComponent} from './penduduk/add-penduduk/add-penduduk.component';
import {NgModule} from '@angular/core';
import {DefaultLayoutComponent} from './default-layout/default-layout.component';

const appRoutes: Routes = [
  {
    path: 'form',
    component: DefaultLayoutComponent,
    children: [
      {path: '', component: ListPendudukComponent},
      {path: 'new', component: AddPendudukComponent},
      {path: ':id', component: UpdatePendudukComponent}
    ]
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: false}
    )
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
