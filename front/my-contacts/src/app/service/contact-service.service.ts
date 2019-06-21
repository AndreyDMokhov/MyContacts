import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import { tap, map} from "rxjs/operators";

import {Contact} from "../classes/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactServiceService {

  private heroesUrl = '/api/contacts';

  constructor(private http: HttpClient) { }

  getContactsList(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.heroesUrl +'/')
      .pipe(tap(_=> console.log("fetched contacts")));

  }
}
