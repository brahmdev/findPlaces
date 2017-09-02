import { Component } from '@angular/core';

import {Router, RouterModule, Routes}   from '@angular/router';

import {InputTextModule, ButtonModule, MenuModule, MenubarModule, PanelMenu, MenuItem} from 'primeng/primeng';

import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home.component';
import { LoginFormComponent } from "../component/loginForm.component";
import { AuthenticationService } from "../service/authentication.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['../style/app.component.css'],
  providers: [HeaderComponent, NavbarComponent, FooterComponent, HomeComponent, LoginFormComponent]
})
export class AppComponent {

  isUserLoggedIn = false;

  constructor(private auth: AuthenticationService, private router: Router) {
    this.isUserLoggedIn = this.auth.getUserLoggedIn();
  }

}
