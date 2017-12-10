import { Component, OnInit } from '@angular/core';
import { Account } from '../account';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})

export class AccountListComponent implements OnInit {

  private accounts: Account[];

  constructor( private accountService: AccountService) { }

  ngOnInit() {
    this.getAllAccounts();
  }

  getAllAccounts() {
    this.accountService.findAll().subscribe(
      accounts => {
        this.accounts = accounts;
      },
      err => {
        console.log(err);
      }

    );
  }


}
