import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddSupplierComponent } from './add-supplier/add-supplier.component';
import { DisplayDetailsSupplierComponent } from './display-details-supplier/display-details-supplier.component';
import { FormsModule } from '@angular/forms';
import { AddRawMaterialStockComponent } from './add-raw-material-stock/add-raw-material-stock.component';
import { RawMaterialStockServiceService } from './service/raw-material-stock-service.service';
import { TrackRawMaterialStockComponent } from './track-raw-material-stock/track-raw-material-stock.component';
import { HttpClientModule } from '@angular/common/http';
import { SetProcessDateRawMaterialStockComponent } from './set-process-date-raw-material-stock/set-process-date-raw-material-stock.component';
import { UpdateRawMaterialStockComponent } from './update-raw-material-stock/update-raw-material-stock.component';
import { AddDistributorComponent } from './add-distributor/add-distributor.component';
import { DisplayDetailsDistributorComponent } from './display-details-distributor/display-details-distributor.component';
import { SupplierService } from './service/supplier.service';
import { DistributorService } from './service/distributor.service';

@NgModule({
  declarations: [
    AppComponent,
    DisplayDetailsSupplierComponent,
    AddRawMaterialStockComponent,
    AddSupplierComponent,
    TrackRawMaterialStockComponent,
    SetProcessDateRawMaterialStockComponent,
    UpdateRawMaterialStockComponent,
    AddDistributorComponent,
    DisplayDetailsDistributorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [RawMaterialStockServiceService,SupplierService,DistributorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
