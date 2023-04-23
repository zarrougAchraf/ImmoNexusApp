import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Advertising } from '../models/advertising.model';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root',
})
export class AdvertisingService {
  constructor(private httpClient: HttpClient) {}

  getAllAdvertisings(): Observable<Advertising[]> {
    return this.httpClient.get<Advertising[]>(
      'http://localhost:8075/ImmoNexus/Advertising/get-AllAdvertising'
      
    );
  }

  getAdvertisingBetweenTwoDate(start: any, end: any): Observable<Advertising[]> {
    return this.httpClient.post<Advertising[]>(
      `http://localhost:8075/ImmoNexus/Advertising/get-All-Actual-Advertising`,
      {
        start: start,
        end: end
      }
    );
  }

  deleteAdvertising(id: number): any {
    return this.httpClient.delete<Advertising[]>(
      `http://localhost:8075/ImmoNexus/Advertising/delete-Advertising-ById/${id}`,
     
    );
  }


  addAdvertising(ad: any): any {
    return this.httpClient.post<Advertising[]>(
      `http://localhost:8075/ImmoNexus/Advertising/add-Advertising`,
      ad
    );
  }


  // getUserById(id: number): Observable<User> {
  //   return this.httpClient.get<User>(
  //     http://localhost:8080/user/getUser/${id}
  //   );
  // }

  // addUser(user: User): Observable<User> {
  //   return this.httpClient.post<User>(
  //     http://localhost:8080/user/addUser,
  //     user
  //   );
  // }

  // updateUser(user: User): Observable<User> {
  //   return this.httpClient.put(http://localhost:8080/user/update, user);
  // }

  // getUserByNom(nom: string): Observable<User> {
  //   return this.httpClient.get<User>(
  //     http://localhost:8080/user/getUserByNom/${nom}
  //   );
  // }

  // deleteUserById(id: number) {
  //    return this.httpClient.delete(
  //     http://localhost:8080/user/delete/${id}
  //   );
  // }
}