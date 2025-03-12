import { Component } from '@angular/core';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [NgIf,],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css',

})
export class AppComponent {
  showFirstDiv: boolean = false;

  toggleDivs() {
    this.showFirstDiv = !this.showFirstDiv;
  }
}
