import { Component } from '@angular/core';
import {Validators,FormControl,FormGroup,FormBuilder} from '@angular/forms';
import {Message,SelectItem, GrowlModule, CaptchaModule} from 'primeng/primeng';
import { Router } from '@angular/router';
import { AuthenticationService } from "../service/authentication.service";
import {GlobalEventsManager} from "../service/GlobalEventsManager";
import {Constants} from "../util/constants";

@Component({
  selector: 'app-login-form',
  templateUrl: './loginForm.component.html',
  styleUrls: ['../style/loginForm.component.css'],
  providers: [ AuthenticationService ]
})
export class LoginFormComponent {

  msgs: Message[] = [];

  loginform: FormGroup;

  submitted: boolean;

  error = '';

  constructor(private fb: FormBuilder, private router: Router, private authenticationService : AuthenticationService, private globalEventsManager: GlobalEventsManager) {}

  ngOnInit() {
    this.loginform = this.fb.group({
      'username': new FormControl('', Validators.required),
      'password': new FormControl('', Validators.required)
    });

  }

  showResponse(event) {
    console.log(event.name);
  }

  loginUser(value: any) {
debugger;
    this.submitted = true;

    let username = value.username;
    let password = value.password;

    if(username == 'sb' && password == "sb") {
      localStorage.setItem(Constants.CURRENT_USER, JSON.stringify(username));
      this.router.navigate(['/' + Constants.HOME_ROUTER_LINK]);
    } else {
       this.error = 'Error in login. Please try again.';
            this.msgs = [];
            this.msgs.push({severity:'error', summary:'Error', detail:this.error});
    }
    
  }
}
