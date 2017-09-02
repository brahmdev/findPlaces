import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService} from './authentication.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authenticationService: AuthenticationService) {}
  private isUserLoggedIn = false;
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      //localStorage.removeItem('currentUser')
      this.isUserLoggedIn = true;
      return true;
    }
    this.isUserLoggedIn = false;
    this.router.navigate([''])
    return false;
  }
}
