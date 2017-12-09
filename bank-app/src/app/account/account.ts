import { Client } from './client';
import { Operation } from './operation';

export class Account {
  id: number;
  balance: number;
  client: Client;
  operations: Operation[];
}
