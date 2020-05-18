import { Component, OnInit } from '@angular/core';
import { RawMaterialStock } from '../model/raw-material-stock';
import { RawMaterialStockServiceService } from '../service/raw-material-stock-service.service';

@Component({
  selector: 'app-update-raw-material-stock',
  templateUrl: './update-raw-material-stock.component.html',
  styleUrls: ['./update-raw-material-stock.component.css']
})
export class UpdateRawMaterialStockComponent implements OnInit {

  service:RawMaterialStockServiceService;
  errorMsg: String;
  constructor(service:RawMaterialStockServiceService) { 
    this.service=service;
  }

  ngOnInit(): void {
  }

  stock:RawMaterialStock=null;
  updateStock(form:any){
    console.log("hii");
    this.stock=null;
    let details=form.value;
    let id=details.id;
    let manuDate=details.manDate;
    let exDate=details.exDate;
    let chk=details.chk;
    let updateStock:RawMaterialStock=new RawMaterialStock();
    updateStock.orderId=id;
    updateStock.manufacturingDate=manuDate;
    updateStock.expiryDate=exDate;
    updateStock.qualityCheck=chk;
    console.log("upadate1");
    let result=this.service.update(updateStock);
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
    console.log("update4");
    form.reset();
  
  }

}
