import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackRawMaterialStockComponent } from './track-raw-material-stock.component';

describe('TrackRawMaterialStockComponent', () => {
  let component: TrackRawMaterialStockComponent;
  let fixture: ComponentFixture<TrackRawMaterialStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrackRawMaterialStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackRawMaterialStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
