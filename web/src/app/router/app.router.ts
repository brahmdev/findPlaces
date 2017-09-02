import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import  { HomeComponent } from '../component/home.component';
import {AuthGuard} from "../service/auth.guard";
import  { LoginFormComponent } from '../component/loginForm.component';
import  { ShowPlacesComponent } from '../component/showPlaces.component';

export const router : Routes = [

  //{ path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: '', component: LoginFormComponent},
  { path: 'home', canActivate:[AuthGuard],  component: HomeComponent},
  { path: 'showPlaces', canActivate:[AuthGuard],  component: ShowPlacesComponent},
];


export const routes: ModuleWithProviders = RouterModule.forRoot(router);
