import { Rayon } from './../../models/rayon';
import { Component, OnInit } from '@angular/core';
import { RayonService } from 'src/app/services/rayon.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-add-rayon',
  templateUrl: './add-rayon.component.html',
  styleUrls: ['./add-rayon.component.css']
})
export class AddRayonComponent implements OnInit {

  rayon: Rayon = new Rayon();

  constructor(private _rayonService: RayonService,
              private _router: Router,
              private _activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const isIdPresent = this._activatedRoute.snapshot.paramMap.has('id');
    if (isIdPresent) {
        const id = +this._activatedRoute.snapshot.paramMap.get('id');
        this._rayonService.getRayon(id).subscribe(
          data => this.rayon = data 
        )
    }
  }

  saveRayon() {
    this._rayonService.saveRayon(this.rayon).subscribe(
      data => {
        console.log('response', data);
        this._router.navigateByUrl("/rayons");
      }
    )
  }

  deleteRayon(id: number) {
    this._rayonService.deleteRayon(id).subscribe(
      data => {
        console.log('deleted response', data);
        this._router.navigateByUrl('/rayons');
      }
    )
  }

}