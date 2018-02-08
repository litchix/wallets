import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Coin} from "./model/coins";

@Injectable()
export class PricingService {

  //Asynchrone
  coins: Coin[];

  constructor(public  http: HttpClient) {

  }


  loadPrices() {
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';

    function mapper(coin) {
      return {
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up: (coin.percent_change_1h < 0) ? false : true
      }
    }

    return this.http.get(url)
      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper))
      .then(joliCoins => {
        this.coins = joliCoins;
        return joliCoins;
      });
  }

  getColor(symbol) {
    let coin = this.coins.find(coin => coin.symbol === symbol);
    if (symbol === 'USD') {
      return 'black';
    } else if (coin.up == true) {
      return 'green';
    } else {
      return 'red';
    }
  }

  priceToDollar(quantity, symbol) {
    for (let i = 0; i < this.coins.length; i++) {
      let coin = this.coins[i];

      if (symbol === coin.symbol) {
        //console.log('Money : ', coin.name);
        return quantity * coin.price;
      }
    }
  }

}
