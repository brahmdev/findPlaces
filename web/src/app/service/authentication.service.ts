import { Injectable, EventEmitter } from '@angular/core';

import {Http, Headers, URLSearchParams} from "@angular/http";

import 'rxjs/Rx';
import {Router} from "@angular/router";
import {GlobalEventsManager} from "./GlobalEventsManager";
import {User } from "../modal/User";
import {AuthRequest } from "../modal/AuthRequest";
import {Observable} from "rxjs/Observable";
import {Constants} from "../util/constants";
import {CommonService} from "./CommonService";

@Injectable()
export class AuthenticationService {

  private isUserLoggedIn = true;
  private username;
  private user: User;

  public onLogin: EventEmitter<any> = new EventEmitter;
  public onLogout: EventEmitter<any> = new EventEmitter;

  constructor (private http : Http, private router: Router, private globalEventsMangaer : GlobalEventsManager) {
    this.isUserLoggedIn = this.getUserLoggedIn();
  }

  setUserLoggedIn() {
    this.isUserLoggedIn = true;
   }

  getUserLoggedIn() {
    debugger;
     if(localStorage.getItem('currentUser')) {
      this.isUserLoggedIn = true;
      this.globalEventsMangaer.isUserLoggedIn(true);
      return true;
    } else {
      return false;
    }
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.isUserLoggedIn = false;
    this.user = null;
    this.globalEventsMangaer.isUserLoggedIn(false);
    this.onLogout.emit();
    this.router.navigate(['']);
  }

}
