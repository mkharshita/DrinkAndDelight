import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRawMaterialStockComponent } from './add-raw-material-stock.component';

describe('AddRawMaterialStockComponent', () => {
  let component: AddRawMaterialStockComponent;
  let fixture: ComponentFixture<AddRawMaterialStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddRawMaterialStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRawMaterialStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
