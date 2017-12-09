import { Component, OnInit,Input } from '@angular/core';
import { Account } from "../account";
import { AccountService } from "../account.service";
import { ActivatedRoute , Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css'],
  providers: [AccountService]
})
export class AccountDetailComponent implements OnInit {
  @Input() account: Account;
   accountForm: FormGroup;

  
  constructor( private accountService: AccountService,
               private route: ActivatedRoute,
 private router: Router) { }

  ngOnInit() {
  this.getAccount();
  this.accountForm = new FormGroup ({
     amount: new FormControl('', [
      Validators.required,
      Validators.min(0),])
  });
  }
  getAccount():void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.accountService.findById(id).subscribe( account => this.account = account);
  }
   onSubmit() {
   this.accountService.depositAccount(this.account.id,this.accountForm.controls['amount'].value).subscribe();
   this.accountForm.reset();
   this.router.navigate(['/account']);
   }
}
