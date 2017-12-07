import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientCreateComponent } from './client-create/client-create.component';
 
const routes: Routes = [
  {path: 'client', component: ClientListComponent},
  {path: 'client/create', component: ClientCreateComponent},
  {path: 'client/edit/:id', component: ClientCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }

 

