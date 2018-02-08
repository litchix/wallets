import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  formModel = {
    name: '',
    limit: 1000,
    level: 'Noob'
  };

  levels = ['Noob', 'Expert', 'Master'];

  constructor() {
  }

  ngOnInit() {
  }

  createNewWallet() {
    console.log((this.formModel));
  }

  isDisabled() {
    return this.formModel.name === '';
  }
}
