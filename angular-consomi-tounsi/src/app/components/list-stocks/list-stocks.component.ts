import { Stock } from './../../models/stock';
import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/app/services/stock.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-list-stocks',
  templateUrl: './list-stocks.component.html',
  styleUrls: ['./list-stocks.component.css']
})
export class ListStocksComponent implements OnInit {
  stocks: Stock[] = [];
  filters = {
    keyword: '',
    sortBy: 'Name'
  }


  constructor(private _stockService: StockService) { }

  ngOnInit(): void {
    this.listStocks();
  }
  deleteStock(id: number) {
    this._stockService.deleteStock(id).subscribe(
      data => {
        console.log('deleted response', data);
        this.listStocks();
      }
    )
    
  }

  listStocks() {
    this._stockService.getStocks().subscribe(
      data => this.stocks = this.filterStocks(data)
    )
  }

  filterStocks(stocks: Stock[]) {
    return stocks.filter((e) => {
      return String(e.id).toLowerCase().includes(this.filters.keyword.toLowerCase());
    }).sort((a, b) => {
      if (this.filters.sortBy === 'Name') {
        return a.stockName.toLowerCase() < b.stockName.toLowerCase() ? -1: 1;
      }else if(this.filters.sortBy === 'Amount') {
        return a.id > b.id ? -1: 1;
      }
    })
  }
}
