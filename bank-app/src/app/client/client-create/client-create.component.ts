import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClientService} from "../client.service";
import {Client} from "../client";
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.css'],
  providers: [ClientService]
})
export class ClientCreateComponent implements OnInit, OnDestroy {


  id: number;
  client: Client;

  clientForm: FormGroup;
  private sub: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private clientService: ClientService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.clientForm = new FormGroup({
    username: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      password: new FormControl('',Validators.required)
      
    });



    if (this.id) { //edit form
      this.clientService.findById(this.id).subscribe(
        client => {
          this.id = client.id;
          this.clientForm.patchValue({
          username: client.username,
            firstName: client.firstName,
            lastName: client.lastName,
          });
        },error => {
          console.log(error);
        }
      );

    }


  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  onSubmit() {
    if (this.clientForm.valid) {
      if (this.id) {
        let client: Client = new Client(this.id,
          this.clientForm.controls['username'].value,
          this.clientForm.controls['firstName'].value,
          this.clientForm.controls['lastName'].value,
          this.clientForm.controls['password'].value,
          
          );
        this.clientService.updateClient(client).subscribe();
      } else {
        let client: Client = new Client(null,
          this.clientForm.controls['username'].value,
          this.clientForm.controls['firstName'].value,
          this.clientForm.controls['lastName'].value,
          this.clientForm.controls['password'].value,);
        this.clientService.saveClient(client).subscribe();

      }

      this.clientForm.reset();
      this.router.navigate(['/client']);

    }
  }

  redirectClientPage() {
    this.router.navigate(['/client']);

  }

}


