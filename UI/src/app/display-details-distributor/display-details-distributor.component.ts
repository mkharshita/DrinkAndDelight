import { Component, OnInit } from '@angular/core';
import { Distributor } from '../model/distributor';
import { DistributorService } from '../service/distributor.service';

@Component({
  selector: 'app-display-details-distributor',
  templateUrl: './display-details-distributor.component.html',
  styleUrls: ['./display-details-distributor.component.css']
})
export class DisplayDetailsDistributorComponent implements OnInit {

  distributors:Distributor[];
  distributor:Distributor=null;
  service:DistributorService;
  errorMsg:String;
  constructor(service:DistributorService) {
    this.service=service;
    let result= this.service.getAll();
    result.subscribe((distributors:Distributor[])=>{
      this.distributors=distributors;
    },
    err=>{
      console.log("error:"+err);
    });
   }

  ngOnInit(): void {
  }
  findDistributor(form:any){
    this.distributor=null;
    let details=form.value;
    let id=details.id;
    let result = this.service.findDistributorById(id);
    result.subscribe((distributor:Distributor)=>{
      this.distributor=distributor;
      this.errorMsg=null;
    },
    err=>{
      this.distributor=null;
      let x=JSON.stringify(err);
      console.log("error:"+x);
      this.errorMsg=err.error;
      console.log("error msg:"+this.errorMsg);
    });
    form.reset();
  }

}
