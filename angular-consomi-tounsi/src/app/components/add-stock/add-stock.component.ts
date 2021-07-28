import { Stock } from './../../models/stock';
import { Rayon } from './../../models/rayon';
import { Component, OnInit } from '@angular/core';
import { StockService } from 'src/app/services/stock.service';
import { RayonService } from 'src/app/services/rayon.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-add-stock',
  templateUrl: './add-stock.component.html',
  styleUrls: ['./add-stock.component.css']
})
export class AddStockComponent implements OnInit {
  rayons: Rayon[] = [];
  errorMessage ="";
  errStock =false;

  stock: Stock = new Stock();

  constructor(private _stockService: StockService,
              private _router: Router,
              private _activatedRoute: ActivatedRoute,
              private _rayonService: RayonService) { }
              

  ngOnInit(): void {
    
    const isIdPresent = this._activatedRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
        const id = +this._activatedRoute.snapshot.paramMap.get('id');
        this._stockService.getStock(id).subscribe(
          data =>{ this.stock = data },
          
          
        )
        
        
    }
    this._rayonService.getRayons().subscribe(
      data => this.rayons = data
    )
  }

  saveStock() {
    this._stockService.saveStock(this.stock).subscribe(
      data => {
        console.log('response', data);
        this._router.navigateByUrl("/stocks");
       this.errStock=false;},
        err => {this.errorMessage =err.error.message;
          console.log("azert"+this.errorMessage);
        this.errStock=true;}
      
    )
  }

  deleteStock(id: number) {
    this._stockService.deleteStock(id).subscribe(
      data => {
        console.log('deleted response', data);
        this._router.navigateByUrl('/stocks');
      }
    )
  }

}