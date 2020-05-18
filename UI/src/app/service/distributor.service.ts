import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Distributor } from '../model/distributor';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class DistributorService {
  client:HttpClient;
  constructor(client:HttpClient){
    this.client=client;
  }
  baseUrl = "http://localhost:8086/distributor";

    getAll():Observable<Distributor[]>{
      let url=this.baseUrl;
      let result:Observable<Distributor[]>=this.client.get<Distributor[]>(url);
      return result;
    }
    addDistributor(distributor:Distributor): Observable<Distributor>{
        let url = this.baseUrl+"/add";
        let result :Observable<Distributor>=this.client.post<Distributor>(url,distributor);
        return result;
    }
    findDistributorById(id:String):Observable<Distributor>{
      let url = this.baseUrl+"/getbyid/"+id;
      let result:Observable<Distributor>=this.client.get<Distributor>(url);
      return result;
    }
}
