import { Component, OnInit,Input } from '@angular/core';
import { Client } from "../client";
import { ClientService } from "../client.service";
import { Account } from "../../account/account";
import { AccountService } from "../../account/account.service";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css'],
  providers: [ClientService,AccountService]
})
export class ClientDetailComponent implements OnInit {
  @Input() client: Client;
  
  private accounts: Account[];
 
  constructor( private clientService: ClientService,
               private accountService: AccountService,
               private route: ActivatedRoute) { }

  ngOnInit() {
  this.getClient();
  }
  getClient():void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.clientService.findById(id).subscribe( client => this.client = client);
   this.accountService.findAllByClient(id).subscribe(
      accounts => {
        this.accounts = accounts;
      },
      err => {
        console.log(err);
      }
 
    );
  } 
  
 
}
