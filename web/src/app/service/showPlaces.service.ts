import { Injectable } from '@angular/core';

import { Places} from '../modal/Places';
import {Http, Headers, URLSearchParams} from "@angular/http";

import 'rxjs/Rx';

@Injectable()
export class ShowPlacesService {

  constructor (private http : Http) {}

  giveMePlaces(location, radius, type, keyword) {
    var params = 'type=' + type + "&keyword=" + keyword;
    console.log(params);
    var headers = new Headers();
    headers.append('Content-Type','application/x-www-form-urlencoded; charset=UTF-8');

    return this.http.post('service/findPlaces/' + location + '/' + radius, params, {
      headers : headers
    }).map(res => <Places[]>res.json());
  }

  
}
