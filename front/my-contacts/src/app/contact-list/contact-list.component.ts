import { Component, OnInit } from '@angular/core';
import {Contact} from "../classes/contact";
import {animate, state, style, transition, trigger} from '@angular/animations';
import {ContactServiceService} from "../service/contact-service.service";

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class ContactListComponent implements OnInit {
  columnsToDisplay = [ {db: 'firstName', show: 'Name'}, {db: 'lastName', show: 'Last name'}];

  contacts: Contact[] = [];
  expandedElement: Contact | null;
  dataSource = this.contacts;
  constructor(private contactService: ContactServiceService) { }

  ngOnInit() {
    this.getContact();
  }
getContact(): void {
    this.contactService.getContactsList()
      .subscribe(contacts => {
        this.contacts = contacts;
        this.dataSource=contacts;
        console.log(this.contacts);
      })
}
}
