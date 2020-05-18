import { Component, OnInit } from '@angular/core';
import { RawMaterialStockServiceService } from '../service/raw-material-stock-service.service';
import { RawMaterialStock } from '../model/raw-material-stock';

@Component({
  selector: 'app-track-raw-material-stock',
  templateUrl: './track-raw-material-stock.component.html',
  styleUrls: ['./track-raw-material-stock.component.css']
})
export class TrackRawMaterialStockComponent implements OnInit {

  stocks:RawMaterialStock[];
  service:RawMaterialStockServiceService;
  errorMsg: String;
  constructor(service:RawMaterialStockServiceService) {
    this.service=service;
    let result= this.service.getAll();
    result.subscribe((stocks:RawMaterialStock[])=>{
      this.stocks=stocks;
    },
    err=>{
      console.log("error:"+err);
    });
   }

  ngOnInit(): void {
  }

  stock:RawMaterialStock=null;
  findStockById(form:any){
    console.log("findstock1");
    this.stock=null;
    let details=form.value;
    let id=details.id;
    let result = this.service.findStockById(id);
    result.subscribe((stock:RawMaterialStock)=>{
      this.stock=stock;
    },
    err=>{
      this.stock=null;
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    form.reset();
  }
}
