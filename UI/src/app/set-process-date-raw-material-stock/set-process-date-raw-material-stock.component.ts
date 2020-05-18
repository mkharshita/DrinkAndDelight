import { Component, OnInit } from '@angular/core';
import { RawMaterialStockServiceService } from '../service/raw-material-stock-service.service';
import { RawMaterialStock } from '../model/raw-material-stock';

@Component({
  selector: 'app-set-process-date-raw-material-stock',
  templateUrl: './set-process-date-raw-material-stock.component.html',
  styleUrls: ['./set-process-date-raw-material-stock.component.css']
})
export class SetProcessDateRawMaterialStockComponent implements OnInit {

  service:RawMaterialStockServiceService;
  errorMsg: String;
  constructor(service:RawMaterialStockServiceService) { 
    this.service=service;
  }

  ngOnInit(): void {
  }

  stock:RawMaterialStock=null;
  setProcessDate(form:any){
    console.log("hii");
    this.stock=null;
    let details=form.value;
    let id=details.id;
    let processDate=details.processDate;
    let updateStock:RawMaterialStock=new RawMaterialStock();
    updateStock.orderId=id;
    updateStock.processDate=processDate;
    let result=this.service.setProcessDate(updateStock);
    result.subscribe((stock:RawMaterialStock)=>{
      this.stock=stock;
      this.errorMsg=null;
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
