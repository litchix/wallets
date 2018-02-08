import {Injectable} from '@angular/core';
import {BunService} from "./bun.service";
import {SteakService} from "./steak.service";

@Injectable()
export class BurgerService {


  constructor(public bunService:BunService,
              public steakService:SteakService) {
//cree automatiquement les nouveaux attributs
  }

  getPrice() {
    return 10;
  }
}
