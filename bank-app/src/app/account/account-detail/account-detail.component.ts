import { Component, OnInit, Input } from '@angular/core';
import { Account } from '../account';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css'],
  providers: [AccountService]
})
export class AccountDetailComponent implements OnInit {
  @Input() account: Account;
  accountForm: FormGroup;


  constructor(private accountService: AccountService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.getAccount();
    this.accountForm = new FormGroup({
      amount: new FormControl('', [
        Validators.required,
        Validators.min(0), ]),
      operation: new FormControl('', Validators.required)
    });
  }
  getAccount(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.accountService.findById(id).subscribe(account => this.account = account);
  }

  onOperationChange(): void {
    if (this.accountForm.controls['operation'].value === 'Withdrawal') {

      this.accountForm.controls['amount'].setValidators([Validators.required, Validators.min(0), Validators.max(this.account.balance), ]);
    } else {

      this.accountForm.controls['amount'].setValidators([Validators.required, Validators.min(0), ]);
    }

  }
  onSubmit() {
    if (this.accountForm.controls['operation'].value === 'Deposit') {
      this.accountService.depositAccount(this.account.id, this.accountForm.controls['amount'].value).subscribe();
    }
    // tslint:disable-next-line:one-line
    else {
      this.accountService.withdrawalAccount(this.account.id, this.accountForm.controls['amount'].value).subscribe();
    }
    this.accountForm.reset();
    this.router.navigate(['/account']);
  }
}
