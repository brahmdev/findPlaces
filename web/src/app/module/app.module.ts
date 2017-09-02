import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routes } from '../router/app.router';

import { AppComponent } from '../component/app.component';
import { HeaderComponent } from '../component/header/header.component';
import { NavbarComponent } from '../component/navbar/navbar.component';
import { FooterComponent } from '../component/footer/footer.component';
import { HomeComponent } from "../component/home.component";
import { LoginFormComponent } from "../component/loginForm.component";
import { ShowPlacesComponent } from "../component/showPlaces.component";

import {InputTextModule, ButtonModule, MenuModule, MenubarModule, DataTableModule, DialogModule, PanelModule, GrowlModule, DropdownModule, ConfirmDialogModule, AutoCompleteModule, FieldsetModule, SharedModule, DataListModule} from 'primeng/primeng';


import {AuthGuard} from "../service/auth.guard";
import {AuthenticationService} from "../service/authentication.service";
import {GlobalEventsManager} from "../service/GlobalEventsManager";


@NgModule({
  declarations: [
    AppComponent, HeaderComponent, NavbarComponent, FooterComponent, HomeComponent, LoginFormComponent, ShowPlacesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routes,
    InputTextModule,
    ButtonModule,
    MenuModule,
    MenubarModule,
    FormsModule,
    ReactiveFormsModule,
    DataTableModule,
    DataListModule,
    SharedModule,
    DialogModule,
    InputTextModule,
    ButtonModule,
    MenuModule,
    MenubarModule,
    PanelModule,
    BrowserAnimationsModule,
    GrowlModule,
    DropdownModule,
    ConfirmDialogModule,
    AutoCompleteModule,
    FieldsetModule
  ],
  providers: [AuthGuard, AuthenticationService, GlobalEventsManager],
  bootstrap: [AppComponent]
})
export class AppModule { }
