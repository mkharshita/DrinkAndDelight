import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';
import { SupplierService } from '../service/supplier.service';

@Component({
  selector: 'app-display-details-supplier',
  templateUrl: './display-details-supplier.component.html',
  styleUrls: ['./display-details-supplier.component.css']
})
export class DisplayDetailsSupplierComponent implements OnInit {

  suppliers:Supplier[]
  supplier:Supplier=null;
  service:SupplierService;
  errorMsg: String;
  constructor(service:SupplierService) {
    this.service=service;
    let result= this.service.getAll();
    result.subscribe((suppliers:Supplier[])=>{
      this.suppliers=suppliers;
    },
    err=>{
      console.log("error:"+err);
    });
   }

  ngOnInit(): void {
  }
  findSupplier(form:any){
    let details=form.value;
    let id=details.id;
    let result = this.service.findSupplierById(id);
    result.subscribe((supplier:Supplier)=>{
      this.supplier=supplier;
      this.errorMsg=null;
    },
    err=>{
      this.supplier=null;
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    form.reset();
  }

}
