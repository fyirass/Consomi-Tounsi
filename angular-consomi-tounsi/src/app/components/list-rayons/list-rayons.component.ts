import { Rayon } from './../../models/rayon';
import { Component, OnInit } from '@angular/core';
import { RayonService } from 'src/app/services/rayon.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-list-rayons',
  templateUrl: './list-rayons.component.html',
  styleUrls: ['./list-rayons.component.css']
})
export class ListRayonsComponent implements OnInit {
  rayons: Rayon[] = [];
  filters = {
    keyword: '',
    sortBy: 'Name'
  }


  constructor(private _rayonService: RayonService) { }

  ngOnInit(): void {
    this.listRayons();
  }
  deleteRayon(id: number) {
    this._rayonService.deleteRayon(id).subscribe(
      data => {
        console.log('deleted response', data);
        this.listRayons();
      }
    )
  }

  listRayons() {
    this._rayonService.getRayons().subscribe(
      data => this.rayons = this.filterRayons(data)
    )
  }

  filterRayons(rayons: Rayon[]) {
    return rayons.filter((e) => {
      return String(e.id).toLowerCase().includes(this.filters.keyword.toLowerCase());
    }).sort((a, b) => {
      if (this.filters.sortBy === 'rayonType') {
        return a.rayonType.toLowerCase() < b.rayonType.toLowerCase() ? -1: 1;
      }else if(this.filters.sortBy === 'id') {
        return a.id > b.id ? -1: 1;
      }
    })
  }
}
