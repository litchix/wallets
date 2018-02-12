import {isUndefined} from "util";
import {symbolIteratorPonyfill} from "rxjs/symbol/iterator";
import {createUrlResolverWithoutPackagePrefix} from "@angular/compiler";
import {PricingService} from "../pricing.service";
import {visitValue} from "@angular/compiler/src/util";
import {User} from "./user";
export class Line {
  constructor(public symbol: string, public quantity: number, public value:number) {
  }
}

export class Wallet {
  user:User;
  name:string;
  lines: Line[] = [];
  pricingService: PricingService;

  deposit(dollars: number) {
    //check if (usdline === exist){...}
    let usdline = this.lines.find(line => line.symbol === 'USD');

    if (usdline === undefined) {
      this.lines.push(new Line('USD', dollars, dollars));
    } else {
      usdline.quantity += dollars;

    }
  }

  buy(quantity: number, symbol: string, value:number) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let dollarAmount = usdLine.quantity;

    let coinAmount = this.pricingService.priceToDollar(quantity, symbol);
    //new amount en USD
    usdLine.quantity = dollarAmount - coinAmount;
    usdLine.value = null;
    //new amount du coin

    let symbolLine = this.lines.find(line => line.symbol === symbol);

    if (symbolLine === undefined) {
      this.lines.push(new Line(symbol, quantity, value));
    } else {
      symbolLine.quantity += quantity;
      symbolLine.value += coinAmount;
    }
  }

  totalDollarsValue(): number {
    let totalWallet = 0;
    for (let i = 0; i < this.lines.length; i++) {
      let current = this.lines[i];

      if (current.symbol == 'USD') {
        totalWallet += current.quantity;
      } else {
        totalWallet += this.pricingService.priceToDollar(current.quantity, current.symbol);
      }
    }
    return totalWallet;
    /** Autre facon de faire sans boucle (avec un reduce):
     this.lines.reduce(function (total, line){
      return line.symbol ===‘USD’ ?
        total +line.quantity :
        total+priceToDollar(line.quantity, line.symbol);
    }, 0);
     */

  }

  sell(quantity: number, symbol: string, value:number) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let symbolLine = this.lines.find(line => line.symbol === symbol);
    //new amount dollar
    let Amount = this.pricingService.priceToDollar(quantity, symbol);
    usdLine.quantity = usdLine.quantity + Amount;


    if (symbolLine === undefined) {
      this.lines.push(new Line(symbol, quantity, value));
    } else {
      symbolLine.quantity -= quantity;
      symbolLine.value -= Amount;
    }
  }

}
