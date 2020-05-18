import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayDetailsDistributorComponent } from './display-details-distributor.component';

describe('DisplayDetailsDistributorComponent', () => {
  let component: DisplayDetailsDistributorComponent;
  let fixture: ComponentFixture<DisplayDetailsDistributorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayDetailsDistributorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayDetailsDistributorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
