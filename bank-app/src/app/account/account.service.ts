import { Injectable } from '@angular/core';
import { Account } from './account';
import { Http, Response, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class AccountService {

  private apiUrl = 'http://localhost:8090/accounts';
  private apiUrlOp = 'http://localhost:8090/operations';
  constructor(private http: Http) {
  }

  findById(id: number): Observable<Account> {
    return this.http.get(this.apiUrl + '/' + id)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Error'));
  }


  findAll(): Observable<Account[]> {
    return this.http.get(this.apiUrl)
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  depositAccount(id: number, amount: number): Observable<Account> {
    const urlSearchParams = new URLSearchParams();
    urlSearchParams.append('id', id.toString());
    urlSearchParams.append('amount', amount.toString());

    return this.http.post(this.apiUrlOp + '/deposit', urlSearchParams)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }

  withdrawalAccount(id: number, amount: number): Observable<Account> {
    const urlSearchParams = new URLSearchParams();
    urlSearchParams.append('id', id.toString());
    urlSearchParams.append('amount', amount.toString());

    return this.http.post(this.apiUrlOp + '/withdrawal', urlSearchParams)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }



}