import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../client.service";
import {Client} from "../client";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-client-login',
  templateUrl: './client-login.component.html',
  styleUrls: ['./client-login.component.css'],
   providers: [ClientService]
})
export class ClientLoginComponent implements OnInit {
  loginForm: FormGroup;
   constructor(private route: ActivatedRoute,
              private router: Router,
              private clientService: ClientService) { }

  ngOnInit() {
  this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required) 
    });
  }
  
  onSubmit() {
    if (this.loginForm.valid) {
    this.loginForm.reset();
      this.router.navigate(['/client']);
    }
    
   }

}
