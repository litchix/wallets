import {Component, OnInit} from '@angular/core';
import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  wallet = new Wallet();

  constructor(public pricingService: PricingService) {
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()
      .then(data => console.log(data))
      .then(() => this.initWallet())

    // function without param ; prices are now loaded
  }

  initWallet() {
    this.wallet.deposit(100000);
    this.wallet.buy(0, 'BTC', 0);
    this.wallet.buy(0, 'XRP', 0);
    this.wallet.buy(0, 'ETH', 0);
    this.wallet.buy(0, 'ADA', 0);

  }

  ngOnInit() {
  }

  deposit(value: string) {
    let money = parseFloat(value);
    if (money > 0) {
      this.wallet.deposit(money);
    }
  }

  buy(quantity, symbol, value) {
    this.wallet.buy(parseFloat(quantity), symbol,value);

  }

  getColorWallet(symbol) {
    return this.pricingService.getColor(symbol);
  }

  sell(quantity, symbol, value) {
    this.wallet.sell(parseFloat(quantity), symbol, value)
  }

coinUpdate(){
    return this.pricingService.loadPrices();
}
}
