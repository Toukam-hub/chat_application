import { Injectable } from '@angular/core';
import {Client} from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import {environment} from '../environments/environment';
import {SocketClientState} from './socket-client-state';
import SockJS from 'sockjs-client';
import {filter, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private readonly stompClient: Client;
  private readonly state: BehaviorSubject<SocketClientState>;

  constructor() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS(environment.api),
      reconnectDelay: 5000,
      debug: (msg) => console.log(msg),
      onConnect: () => {
        this.state.next(SocketClientState.CONNECTED);
        console.log('✅ WebSocket connecté');
      },
      onDisconnect: () => {
        this.state.next(SocketClientState.ATTEMPTING);
        console.log('❌ WebSocket déconnecté');
      },
      onStompError: (error) => {
        console.error('🚨 Erreur STOMP:', error);
        this.state.next(SocketClientState.ATTEMPTING);
      }
    });

    this.state = new BehaviorSubject<SocketClientState>(SocketClientState.ATTEMPTING);
    this.stompClient.activate();
  }

  connect(): Observable<Client> {
    return new Observable<Client>(observer => {
      this.state.pipe(filter(state => state === SocketClientState.CONNECTED))
        .subscribe(() => {
        observer.next(this.stompClient);
      });
    });
  }

}
