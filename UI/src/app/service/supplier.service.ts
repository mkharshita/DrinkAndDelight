import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Supplier } from '../model/supplier';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  client:HttpClient;
  constructor(client:HttpClient){
    this.client=client;
  }
  baseUrl = "http://localhost:8086/supplier";

  getAll():Observable<Supplier[]>{
    let url=this.baseUrl;
    let result:Observable<Supplier[]>=this.client.get<Supplier[]>(url);
    return result;
  }

    addSupplier(supplier:Supplier): Observable<Supplier>{
        let url = this.baseUrl+"/add";
        let result :Observable<Supplier>=this.client.post<Supplier>(url,supplier);
        return result;
    }
    findSupplierById(id:String):Observable<Supplier>{
      let url = this.baseUrl+"/getbyid/"+id;
      let result:Observable<Supplier>=this.client.get<Supplier>(url);
      return result;
    }
}
