import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class BunService {

  constructor(public http: HttpClient) {

  }

  getHeight() : Promise<any> {
    let url = 'https://api.coinmarketcap.com/v1/ticker/ethereum/';

    return this.http.get(url).toPromise()
    .then(response =>response[0].percent_change_24h)
    .then( n => Math.abs(n))
    .catch (e => console.log(e) )
  }
}
