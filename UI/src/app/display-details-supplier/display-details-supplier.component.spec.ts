import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayDetailsSupplierComponent } from './display-details-supplier.component';

describe('DisplayDetailsSupplierComponent', () => {
  let component: DisplayDetailsSupplierComponent;
  let fixture: ComponentFixture<DisplayDetailsSupplierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayDetailsSupplierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayDetailsSupplierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
