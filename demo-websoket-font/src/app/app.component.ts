import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [NgIf, FormsModule,],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css',

})
export class AppComponent implements OnInit {
  showFirstDiv: boolean = true;
  desable: boolean = true;
  nickname: string = '';
  realname: string = '';


  ngOnInit(): void {
    const storedState = localStorage.getItem('showFirstDiv');
    this.showFirstDiv = storedState ? JSON.parse(storedState) : true;
  }

  toggleDivs() {
    this.showFirstDiv = !this.showFirstDiv;
    localStorage.setItem('showFirstDiv', JSON.stringify(this.showFirstDiv));
  }

  logout(): void {
    this.showFirstDiv = !this.showFirstDiv;
    localStorage.removeItem('showFirstDiv');
  }

  onSubmit() {
    console.log('Form Submitted!');
    console.log('Nickname:', this.nickname);
    console.log('Real Name:', this.realname);
    this.toggleDivs();
    const formData = { nickname: this.nickname, fullname: this.realname, status: "ONLINE" };

    this.nickname = '';
    this.realname = '';
  }
}
