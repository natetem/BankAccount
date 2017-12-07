import { Component, OnInit } from '@angular/core';
import { Client } from "../client";
import { ClientService } from "../client.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css'],
  providers: [ClientService]
})

export class ClientListComponent implements OnInit {
 
  private clients: Client[];
 
  constructor(private router: Router,private clientService: ClientService) { }
 
  ngOnInit() { 
    this.getAllClients();
  }
 
  getAllClients() {
    this.clientService.findAll().subscribe(
      clients => {
        this.clients = clients;
      },
      err => {
        console.log(err);
      }
 
    );
  }
    redirectNewClientPage() {
    this.router.navigate(['/client/create']);
  }
 
  editClientPage(client: Client) {
    if (client) {
      this.router.navigate(['/client/edit', client.id]);
    }
  }
 
  deleteClient(client: Client) {
   this.clientService.deleteClientById(client.id).subscribe();
      this.router.navigate(['/client']);
  }
 
}
