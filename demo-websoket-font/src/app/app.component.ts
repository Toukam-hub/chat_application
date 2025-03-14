import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {WebsocketService} from '../service/websocket.service';
import {Client} from '@stomp/stompjs';

@Component({
  selector: 'app-root',
  imports: [NgIf, FormsModule,],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css',

})
export class AppComponent implements OnInit {

  showFirstDiv: boolean = true;
  nickname: string = '';
  fullname: string = '';
  isConnected :boolean = false;

  constructor(private readonly webSockectService:WebsocketService) {
  }


  ngOnInit(): void {
    const storedState = localStorage.getItem('showFirstDiv');
    this.showFirstDiv = storedState ? JSON.parse(storedState) : true;
  }

  toggleDivs() {
    this.showFirstDiv = !this.showFirstDiv;
    localStorage.setItem('showFirstDiv', JSON.stringify(this.showFirstDiv));

    this.webSockectService.connect().subscribe({
      next:( client:Client) =>{
        console.log('✅ WebSocket connecté avec succès !');
        this.isConnected = true; // Met à jour la variable si connecté
      },
      error: (err) => {
        console.error('❌ Erreur de connexion WebSocket :', err);
        this.isConnected = false;
      }
    });
  }

  logout(): void {
    this.showFirstDiv = !this.showFirstDiv;
    localStorage.removeItem('showFirstDiv');
  }

  onSubmit() {
    console.log('Form Submitted!');
    console.log('Nickname:', this.nickname);
    console.log('Real Name:', this.fullname);
    this.toggleDivs();
    const formData = { nickname: this.nickname, fullname: this.fullname, status: "ONLINE" };
    console.log("connexion websocket:", this.isConnected);
    this.nickname = '';
    this.fullname = '';
  }
}
