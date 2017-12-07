import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientCreateComponent } from './client-create/client-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientLoginComponent } from './client-login/client-login.component';

@NgModule({
  imports: [
    CommonModule,
    ClientRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [ClientListComponent, ClientCreateComponent, ClientLoginComponent]
})
export class ClientModule { }
