import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Constants } from '../../util/constants';
import {GlobalEventsManager} from "../../service/GlobalEventsManager";
import {AuthenticationService} from "../../service/authentication.service";
import {Container} from "@angular/compiler/src/i18n/i18n_ast";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  activeNavbar = null;
  showNavBar: boolean = false;


  constructor(private router: Router, private globalEventsManager: GlobalEventsManager, private auth : AuthenticationService) {
    router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (event.url.indexOf('/' + Constants.PLACES_ROUTER_LINK + '/') > -1) {
          this.activeNavbar = Constants.PLACES_ROUTER_LINK;
        }
      }
    });

    this.globalEventsManager.showNavBarEmitter.subscribe((mode)=>{
      // mode will be null the first time it is created, so you need to igonore it when null
      
      if (mode !== null) {
        this.showNavBar = mode;
      }
    });
  }

  get homeRouterLink(): string {
    return Constants.HOME_ROUTER_LINK;
  }

  get homeLinkLabel(): string {
    return Constants.HOME_LINK_LABEL;
  }

  get sbMenuLabel(): string {
    return Constants.PLACES_MENU_LINK_LABEL;
  }

  get sbPlacesRouterLink(): string {
    return Constants.PLACES_ROUTER_LINK;
  }

  get sbLinkLabel(): string {
    return Constants.PLACES_LINK_LABEL;
  }

  get showSBPlacesRouterLink(): string {
    return Constants.SHOW_SB_PLACES_ROUTER_LINK;
  }

  get showSBPlacesLinkLabel(): string {
    return Constants.SHOW_SB_PLACES_LINK_LABEL;
  }

   get helpRouterLink(): string {
    return Constants.HELP_ROUTER_LINK;
  }

  get helpLinkLabel(): string {
    return Constants.HELP_LINK_LABEL;
  }

  hideSecondaryNavbar() {
    this.activeNavbar = '';
  }

  toggleSBPlacesNavbar() {
    if (this.activeNavbar === Constants.PLACES_ROUTER_LINK) {
      this.hideSecondaryNavbar();
    } else {
      this.activeNavbar = Constants.PLACES_ROUTER_LINK;
    }
  }

}
