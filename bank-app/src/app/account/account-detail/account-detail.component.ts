import { Component, OnInit } from '@angular/core';
import { Account } from '../account';

import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {
  account: Account;
  accountForm: FormGroup;
  operation: string;

  constructor(private accountService: AccountService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    setInterval(() => {
      this.getAccount();
    }, 1000);


  }
  getAccount(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.accountService.findById(id)
      .subscribe(account => this.account = account);

  }

  deposit(): void {
    this.operation = 'Deposit';
    this.accountForm = new FormGroup({
      amount: new FormControl('', [
        Validators.required,
        Validators.min(0)])
    });
  }

  withdrawal(): void {
    this.operation = 'Withdrawal';
    this.accountForm = new FormGroup({
      amount: new FormControl('', [
        Validators.required,
        Validators.min(0), Validators.max(this.account.balance)])
    });
  }

  historical(): void { this.operation = 'Historical'; }


  onSubmit() {
    if (this.operation === 'Deposit') {
      this.accountService.depositAccount(this.account.id, this.accountForm.controls['amount'].value).subscribe();
    }

    if (this.operation === 'Withdrawal') {
      this.accountService.withdrawalAccount(this.account.id, this.accountForm.controls['amount'].value).subscribe();
    }
    this.accountForm.reset();
    this.operation = '';

  }
}
