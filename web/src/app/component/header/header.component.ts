import {Component, OnInit} from '@angular/core';
import { Message, SelectItem } from 'primeng/primeng';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";
import {GlobalEventsManager} from "../../service/GlobalEventsManager";
import {Constants} from "../../util/constants";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit  {

  showUserProfileComponent = false;

  userId : string = null;

  userName : string = null;

  emailId : string = null;

  constructor(private router: Router, private auth: AuthenticationService, private globalEventsManager: GlobalEventsManager) {

    this.globalEventsManager.showNavBarEmitter.subscribe((mode)=>{
      // mode will be null the first time it is created, so you need to igonore it when null
      if (mode !== null) {
        this.showUserProfileComponent = mode;
        let user = JSON.parse(localStorage.getItem(Constants.CURRENT_USER));
        this.emailId = user.email;
        this.userName = user.firstName + " " + user.lastName;
        this.userId = user.username;
      }
    });


  }

  ngOnInit() {

  }
  errorMessages: Message[] = [];

  userRole = 'User';

  languageList: SelectItem[];
  selectedLanguage: string;

  logout() {
    this.auth.logout();

  }

}
