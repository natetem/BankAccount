import { Component, OnInit } from '@angular/core';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css'],
  providers: [AccountService]
})

export class AccountListComponent implements OnInit {

  private accounts: Account[];

  constructor(private router: Router, private accountService: AccountService) { }

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
