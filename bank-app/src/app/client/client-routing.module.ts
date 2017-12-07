import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientCreateComponent } from './client-create/client-create.component';
import { ClientLoginComponent } from './client-login/client-login.component';
const routes: Routes = [
  {path: 'client', component: ClientListComponent},
   {path: 'client/login', component: ClientLoginComponent},
  {path: 'client/create', component: ClientCreateComponent},
  {path: 'client/edit/:id', component: ClientCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }

 

