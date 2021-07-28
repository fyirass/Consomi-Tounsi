import { Rayon } from './../models/rayon';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RayonService {

  private getUrl: string = "http://localhost:8081/rayons/getall";
  private getoneUrl: string = "http://localhost:8081/rayons/getone";
  private addUrl: string = "http://localhost:8081/rayons/add";
  private updateUrl: string = "http://localhost:8081/rayons/update";
  private deleteUrl: string = "http://localhost:8081/rayons/delete";

  constructor(private _httpClient: HttpClient) { }
  getRayons(): Observable<Rayon[]> {
    return this._httpClient.get<Rayon[]>(this.getUrl).pipe(
      map(response => response)
    )
  }
  saveRayon(rayon: Rayon): Observable<Rayon> {
    return this._httpClient.post<Rayon>(this.addUrl, rayon);
  }

  getRayon(id: number): Observable<Rayon> {
    return this._httpClient.get<Rayon>(`${this.getoneUrl}/${id}`).pipe(
      map(response => response)
    )
  }

  deleteRayon(id: number): Observable<any> {
    return this._httpClient.delete(`${this.deleteUrl}/${id}`, {responseType: 'text'});
  }
}
