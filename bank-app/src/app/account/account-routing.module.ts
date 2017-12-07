import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';
 
const routes: Routes = [
  {path: 'account', component: AccountListComponent},
  {path: 'account/create', component: AccountDetailComponent},
  {path: 'account/edit/:id', component: AccountDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
