import { Component } from '@angular/core';
import { AuthenticationService } from "../service/authentication.service";

@Component({
  selector: 'home',
  templateUrl: './home.component.html'
})
export class HomeComponent {
  isUserLoggedIn = false;
  constructor(private auth : AuthenticationService) {
    this.isUserLoggedIn = this.auth.getUserLoggedIn();
  }
}
