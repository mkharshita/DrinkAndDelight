import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RawMaterialStock } from '../model/raw-material-stock';
import { Observable ,observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RawMaterialStockServiceService {
  client:HttpClient;
  constructor(client:HttpClient){
    this.client=client;
  }
  baseUrl = "http://localhost:8086/RMStock";

  url="https://github.com/shaik-afreen09/Angular_DrinkandDelight/tree/master/src/app"
  getAll():Observable<RawMaterialStock[]>{
    let url=this.baseUrl;
    let result:Observable<RawMaterialStock[]>=this.client.get<RawMaterialStock[]>(url);
    return result;
  }

    addStock(stock:RawMaterialStock): Observable<RawMaterialStock>{
        let url = this.baseUrl+"/add";
        let result :Observable<RawMaterialStock>=this.client.post<RawMaterialStock>(url,stock);
        return result;
    }
    findStockById(id:String):Observable<RawMaterialStock>{
      let url = this.baseUrl+"/TrackRawMaterial/"+id;
      let result:Observable<RawMaterialStock>=this.client.get<RawMaterialStock>(url);
      return result;
    }

    
     update(stock:RawMaterialStock):Observable<RawMaterialStock>{
       console.log("update2");
      let url=this.baseUrl+"/Update/"+stock.orderId;
      let result:Observable<RawMaterialStock>=this.client.put<RawMaterialStock>(url,stock);
      console.log("update3");
      return result;
    }   
    
    setProcessDate(stock:RawMaterialStock):Observable<RawMaterialStock>{
      console.log("update2");
     let url=this.baseUrl+"/UpdateProcessDate/"+stock.orderId;
     let result:Observable<RawMaterialStock>=this.client.put<RawMaterialStock>(url,stock);
     console.log("update3");
     return result;
   }   
}
