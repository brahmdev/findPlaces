import { Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {Constants} from "../util/constants";


export class CommonService {
  static JSON_CONTENT_TYPE_HEADER = new RequestOptions({
    headers: new Headers({
      'Content-Type': 'application/json',
      'Cache-Control': 'no-cache',
      'Pragma': 'no-cache'
    })
  });

  static handleError(error: Response | any): Observable<any> {
    console.log(error);

    let errMsg: string;

    if (error instanceof Response) {
      const body = error.json() || '';
      errMsg = body.message ? body.message : `${error.status} - ${error.statusText || ''} (${body.exception})`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }

    return Observable.throw(errMsg);
  }

  static handleBlobError(error: Response | any): Observable<any> {
    console.log(error);

    let errMsg: string;

    if (error instanceof Response) {
      errMsg = error.headers.get(Constants.ERROR_MESSAGE_HEADER);
    } else {
      errMsg = error.message ? error.message : error.toString();
    }

    return Observable.throw(errMsg);
  }

  static handleAuthError(error: Response | any): Observable<any> {
    debugger;
    console.log(error);

    let errMsg = 'Error occurred during Login';

    if (error instanceof Response) {
      if (error.status === 401) {
        errMsg += ': Wrong User Name and / or Password.';
      } else if (error.status === 403) {
        errMsg += ': Unauthorized Access. Please contact system administrator.';
      } else {
        errMsg += '. Please try again later or contact system administrator.';
      }
    } else {
      errMsg += error.message ? error.message : error.toString();
    }

    return Observable.throw(errMsg);
  }
}
