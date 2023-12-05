import { Routes } from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {AccountsComponent} from "./accounts/accounts.component";

export const routes: Routes = [
  { path : "customers" , component : CustomersComponent},
  { path : "accounts", component : AccountsComponent}
];
