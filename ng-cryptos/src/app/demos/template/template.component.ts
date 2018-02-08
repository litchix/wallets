import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']
})
export class TemplateComponent implements OnInit {

data = 'Some attribute value';
favoriteColor = 'blue';

  coins = [
    {
      name: 'Bitcoin',
      symbol: 'BTC',
      price: 10300
    },
    {
      name: 'Ethereum',
      symbol: 'ETH',
      price: 1100
    },
    {
      name: 'Ripple',
      symbol: 'XRP',
      price: 1.13
    }, {
      name: 'Bitcoin Cash',
      symbol: 'BCH',
      price: 1500
    }, {
      name: 'Cardano',
      symbol: 'ADA',
      price: 0.5
    }, {
      name: 'Stellar',
      symbol: 'XLM',
      price: 0.522
    }, {
      name: 'Néo',
      symbol: 'NEO',
      price: 143.03
    }, {
      name: 'Litecoin',
      symbol: 'LTC',
      price: 163.44
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
