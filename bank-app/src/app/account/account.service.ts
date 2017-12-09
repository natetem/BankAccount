import { Injectable } from '@angular/core';
import { Account } from "./account";
import { Http, Response,URLSearchParams } from "@angular/http";
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import { Observable } from "rxjs/Observable";

 
@Injectable()
export class AccountService {
 
  private apiUrl = 'http://localhost:8090/accounts';
 
  constructor(private http: Http) {
  }
 
  findById(id: number): Observable<Account> {
    return this.http.get(this.apiUrl + '/' + id)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Error'));
  }

  saveAccount(account: Account): Observable<Account> {

    return this.http.post(this.apiUrl, account)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
    
  depositAccount(id:number, amount:number): Observable<Account> {
   let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('id', id);
    urlSearchParams.append('amount', amount);
     
    return this.http.post(this.apiUrl+'/deposit', urlSearchParams)
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }

  deleteAccountById(id: number): Observable<boolean> {
    return this.http.delete(this.apiUrl + '/' + id)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  updateAccount(account: Account): Observable<Account> {
    return this.http.put(this.apiUrl, account)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }

  findAll(): Observable<Account[]>  {
    return this.http.get(this.apiUrl)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
  
   findAllByClient(id: number): Observable<Account[]>  {
    return this.http.get(this.apiUrl+'/client/'+id)
      .map((res:Response) => res.json())
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
 
}