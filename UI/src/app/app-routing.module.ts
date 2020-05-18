import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddSupplierComponent } from './add-supplier/add-supplier.component';
import { DisplayDetailsSupplierComponent } from './display-details-supplier/display-details-supplier.component';
import { AddRawMaterialStockComponent } from './add-raw-material-stock/add-raw-material-stock.component';
import { TrackRawMaterialStockComponent } from './track-raw-material-stock/track-raw-material-stock.component';
import { AddDistributorComponent } from './add-distributor/add-distributor.component';
import { DisplayDetailsDistributorComponent } from './display-details-distributor/display-details-distributor.component';
import { UpdateRawMaterialStockComponent } from './update-raw-material-stock/update-raw-material-stock.component';
import { SetProcessDateRawMaterialStockComponent } from './set-process-date-raw-material-stock/set-process-date-raw-material-stock.component';


const routes: Routes = [
  {path: 'addSupplier' , component : AddSupplierComponent},
  {path:'searchSupplier',component:DisplayDetailsSupplierComponent},
  {path: 'addDistributor' , component : AddDistributorComponent},
  {path:'searchDistributor',component:DisplayDetailsDistributorComponent},
  {path:'addRawMaterialStock',component:AddRawMaterialStockComponent},
  {path:'trackStock',component:TrackRawMaterialStockComponent},
  {path:'updateStock',component:UpdateRawMaterialStockComponent},
  {path:'setprocessDate',component:SetProcessDateRawMaterialStockComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
