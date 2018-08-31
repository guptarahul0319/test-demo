import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { AdminConfigComponent } from './pages/adminConfig/admin-config.component';
import { CreateGroupComponent } from './pages/createGroup/create-group.component';
import { HttpClientModule } from '@angular/common/http';
import {Group} from 'src/app/_services/group/group.service';
import {AuthenticationService} from './_services/authentication.service';
import { UpdateGroupComponent } from './pages/update-group/update-group.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    DashboardComponent,
    AdminConfigComponent,
    CreateGroupComponent,
    UpdateGroupComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule ,
    FormsModule, ReactiveFormsModule,
  ],
 
  providers: [AuthenticationService, Group],

  bootstrap: [AppComponent]
})
export class AppModule { }
