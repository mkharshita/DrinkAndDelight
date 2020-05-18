import { Component, OnInit } from '@angular/core';
import { Distributor } from '../model/distributor';
import { DistributorService } from '../service/distributor.service';

@Component({
  selector: 'app-add-distributor',
  templateUrl: './add-distributor.component.html',
  styleUrls: ['./add-distributor.component.css']
})
export class AddDistributorComponent implements OnInit {

  distributor:Distributor=null;
  service:DistributorService;
  errorMsg=null;
  constructor(service:DistributorService) {
    this.service=service;
  }

  ngOnInit(): void {
  }

  addDistributor(addform:any){
    let details=addform.value;
    let name=details.name;
    let address=details.address;
    let phone=details.phone;
    let create =new Distributor();
    create.distributorName=name;
    create.distributorAddress=address;
    create.distributorPhoneNumber=phone;
    let result = this.service.addDistributor(create);
    result.subscribe((distributor:Distributor)=>{
      this.distributor=distributor;
      this.errorMsg=null;
    },
    err=>{
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    addform.reset();
  }

}
