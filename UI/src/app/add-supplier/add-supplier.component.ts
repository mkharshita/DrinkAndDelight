import { Component, OnInit } from '@angular/core';
import { Supplier } from '../model/supplier';
import { SupplierService } from '../service/supplier.service';

@Component({
  selector: 'app-add-supplier',
  templateUrl: './add-supplier.component.html',
  styleUrls: ['./add-supplier.component.css']
})
export class AddSupplierComponent implements OnInit {

  supplier:Supplier=null;
  service:SupplierService;
  errorMsg:String;
  constructor(service:SupplierService) {
    this.service=service;
  }

  ngOnInit(): void {
  }

  addSupplier(addform:any){
    this.errorMsg=null;
    let details=addform.value;
    let name=details.name;
    let address=details.address;
    let phone=details.phone;
    let create=new Supplier();
    create.supplierName=name;
    create.supplierAddress=address;
    create.supplierPhoneNumber=phone;
    let result = this.service.addSupplier(create);
    result.subscribe((supplier:Supplier)=>{
      this.supplier=supplier;
    },
    err=>{
      this.supplier=null;
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    addform.reset();
  }
}
