import { Component, OnInit,Input } from '@angular/core';
import { Client } from "../client";
import { ClientService } from "../client.service";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css'],
  providers: [ClientService]
})
export class ClientDetailComponent implements OnInit {
  @Input() client: Client;
 
  constructor( private clientService: ClientService,
               private route: ActivatedRoute) { }

  ngOnInit() {
  this.getClient();
  }
  getClient():void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.clientService.findById(id).subscribe( client => this.client = client);
  }
}
