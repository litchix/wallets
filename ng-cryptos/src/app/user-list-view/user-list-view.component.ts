import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../model/user";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];

  constructor(public dataService: DataService) {
    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users : ', users));
  }

  ngOnInit() {

  }

}
