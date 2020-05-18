import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetProcessDateRawMaterialStockComponent } from './set-process-date-raw-material-stock.component';

describe('SetProcessDateRawMaterialStockComponent', () => {
  let component: SetProcessDateRawMaterialStockComponent;
  let fixture: ComponentFixture<SetProcessDateRawMaterialStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetProcessDateRawMaterialStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetProcessDateRawMaterialStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
