import { Component, OnInit } from '@angular/core';
import {Validators,FormControl,FormGroup,FormBuilder} from '@angular/forms';
import { Message,SelectItem,  ConfirmationService } from 'primeng/primeng';
import { AuthenticationService } from "../service/authentication.service";
import { ShowPlacesService } from "../service/showPlaces.service";
import { Places} from '../modal/Places';
@Component({
  selector: 'showPlaces',
  templateUrl: './showPlaces.component.html',
    providers: [ ShowPlacesService ]
})
export class ShowPlacesComponent implements OnInit {
  isUserLoggedIn = false;
  constructor(private auth : AuthenticationService, private fb: FormBuilder, private showPlacesServie: ShowPlacesService) {
    this.isUserLoggedIn = this.auth.getUserLoggedIn();
  }

    places : Places[];

    msgs: Message[] = [];

    placesform: FormGroup;

    submitted: boolean;

    genders: SelectItem[];

    description: string;

    //cars: Car[];
    
    cols: any[];

    ngOnInit() {
        this.placesform = this.fb.group({
            'location': new FormControl(''),
            'radius': new FormControl(''),
            'keyword': new FormControl(''),
            'type': new FormControl('')
        });

        //this.carService.getCarsSmall().then(cars => this.cars = cars);
        
        this.cols = [
            {field: 'vin', header: 'Vin'},
            {field: 'year', header: 'Year'},
            {field: 'brand', header: 'Brand'},
            {field: 'color', header: 'Color'}
        ];
    }

    findPlaces() { 
        let location = this.placesform.value.location;
        let radius = this.placesform.value.radius;
        let type = this.placesform.value.type;
        let keyword = this.placesform.value.keyword;
        
        if(location == "" && radius == "") {
            this.msgs = [];
            this.msgs.push({severity:'error', summary:'Error', detail:'Location & Radius are mandatory field!'});
        } else {
            this.showPlacesServie.giveMePlaces(location, radius, type, keyword).subscribe(
                data => this.places = data,
                error => alert(error),
                () => this.populateData()
                );
        } 
        return JSON.stringify(this.placesform.value); 
    }

    populateData() {
        this.msgs.push({severity:'success', summary:'Success', detail:'Data for Places Fetched Successfully'});
    }
}
