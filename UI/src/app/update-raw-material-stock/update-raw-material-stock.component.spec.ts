import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRawMaterialStockComponent } from './update-raw-material-stock.component';

describe('UpdateRawMaterialStockComponent', () => {
  let component: UpdateRawMaterialStockComponent;
  let fixture: ComponentFixture<UpdateRawMaterialStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateRawMaterialStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateRawMaterialStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
