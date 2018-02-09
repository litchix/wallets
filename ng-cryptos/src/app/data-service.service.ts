import {Injectable} from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class DataService {

  constructor(public http: HttpClient) {
  }

  fetchUsers(): Promise<User[]> {

    return this.http
      .get('http://localhost:8080/cryptos/api/users')
      .toPromise()
      .then(data => data as User[])

  }


}
