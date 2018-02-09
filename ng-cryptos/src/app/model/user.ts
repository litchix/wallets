//Into the model
import {Wallet} from "./wallet";

export class User {

  id: number;
  name: string;

  //protip: always better to initiate an array
  wallets: Wallet[] = [];

}
