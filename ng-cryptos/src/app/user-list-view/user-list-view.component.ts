import {Component, OnInit} from '@angular/core';
import {DataService} from "../data-service.service";
import {User} from "../model/user";
import {Wallet} from "../model/wallet";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];
  selectedUser: User;

  constructor(public dataService: DataService) {
    dataService.fetchUsers()
      .then(users => this.users = users)
      .then(users => console.log('Users : ', users));

    /**
     dataService.fetchWallets()
     .then(wallets => this.wallets = wallets)
     .then(wallets => console.log('Wallets : ', wallets));
     */
  }

  ngOnInit() {

  }

  details(user: User) {
    //this.selectedUser = user;
   // console.log('You selected', user);
    this.dataService.fetchUserWithWallets(user)
      .then(fullUser => this.selectedUser = fullUser)
      .then(console.log); //What???
  }
}
