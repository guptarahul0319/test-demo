import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent} from './app/pages/login/login.component';
import { DashboardComponent} from './app/pages/dashboard/dashboard.component';
import { AdminConfigComponent } from './app/pages/adminConfig/admin-config.component';
import { CreateGroupComponent } from './app/pages/createGroup/create-group.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'adminConfig', component: AdminConfigComponent },
  { path: 'createGroup', component: CreateGroupComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
