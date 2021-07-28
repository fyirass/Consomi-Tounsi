import { Stock } from './../models/stock';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private getUrl: string = "http://localhost:8081/stocks/getall";
  private getoneUrl: string = "http://localhost:8081/stocks/getone";
  private addUrl: string = "http://localhost:8081/stocks/add";
  private updateUrl: string = "http://localhost:8081/stocks/update";
  private deleteUrl: string = "http://localhost:8081/stocks/delete";

  constructor(private _httpClient: HttpClient) { }
  getStocks(): Observable<Stock[]> {
    return this._httpClient.get<Stock[]>(this.getUrl).pipe(
      map(response => response)
    )
  }
  saveStock(stock: Stock): Observable<Stock> {
    return this._httpClient.post<Stock>(this.addUrl, stock);
  }

  getStock(id: number): Observable<Stock> {
    return this._httpClient.get<Stock>(`${this.getoneUrl}/${id}`).pipe(
      map(response => response)
    )
  }

  deleteStock(id: number): Observable<any> {
    return this._httpClient.delete(`${this.deleteUrl}/${id}`, {responseType: 'text'});
  }
}