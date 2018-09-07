import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent} from './app/pages/login/login.component';
import { DashboardComponent} from './app/pages/dashboard/dashboard.component';
import { AdminConfigComponent } from './app/pages/adminConfig/admin-config.component';
import {UpdateGroupComponent} from 'src/app/pages/update-group/update-group.component';
import { CreateGroupComponent } from './app/pages/createGroup/create-group.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'group/:id/edit', component: UpdateGroupComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'admin', component: AdminConfigComponent },
  { path: 'group', component: CreateGroupComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
