import { Injectable } from '@angular/core';
import { Account } from './account';
import { Http, Response, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AccountService {

  private apiUrl = 'http://localhost:8090/accounts';
  private apiUrlOp = 'http://localhost:8090/operations';
  constructor(private httpOp: Http, private http: HttpClient) {
  }

  findById(id: number): Observable<Account> {
    return this.http.get<Account>(this.apiUrl + '/' + id);

  }


  findAll(): Observable<Account[]> {
    return this.http.get<Account[]>(this.apiUrl);

  }

  depositAccount(id: number, amount: number): Observable<any> {
    const urlSearchParams = new URLSearchParams();
    urlSearchParams.append('id', id.toString());
    urlSearchParams.append('amount', amount.toString());

    return this.httpOp.post(this.apiUrlOp + '/deposit', urlSearchParams)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }

  withdrawalAccount(id: number, amount: number): Observable<any> {
    const urlSearchParams = new URLSearchParams();
    urlSearchParams.append('id', id.toString());
    urlSearchParams.append('amount', amount.toString());

    return this.httpOp.post(this.apiUrlOp + '/withdrawal', urlSearchParams)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }



}