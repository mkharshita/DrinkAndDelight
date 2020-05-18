import { Component, OnInit } from '@angular/core';
import { RawMaterialStockServiceService } from '../service/raw-material-stock-service.service';
import { RawMaterialStock } from '../model/raw-material-stock';

@Component({
  selector: 'app-add-raw-material-stock',
  templateUrl: './add-raw-material-stock.component.html',
  styleUrls: ['./add-raw-material-stock.component.css']
})
export class AddRawMaterialStockComponent implements OnInit {

  service:RawMaterialStockServiceService;
  constructor(service:RawMaterialStockServiceService) {
    this.service=service;
   }
  ngOnInit(): void {
  }

  errorMsg=null;
  stock: RawMaterialStock=null;
  addStock(stockForm:any){
    this.stock=null;
    let details = stockForm.value;
    let sid = details.sid;
    let oid = details.oid;
    let name = details.name;
    let price_unit = details.price_unit;
    let quantityValue = details.quantityValue;
    let quantityUnit = details.quantityUnit;
    let wid = details.wid;
    let date =details.date;
    let create=new RawMaterialStock();
    create.stockId=sid;
    create.orderId=oid;
    create.name=name;
    create.price_per_unit=price_unit;
    create.quantityValue=quantityValue;
    create.quantityUnit=quantityUnit;
    create.warehouseId=wid;
    create.processDate=date;
    let result = this.service.addStock(create);
    result.subscribe((stock:RawMaterialStock)=>{
      this.stock=stock;
      this.errorMsg=null;
    },
    err=>{
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    stockForm.reset();
  }


}
