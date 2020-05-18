import { TestBed } from '@angular/core/testing';

import { RawMaterialStockServiceService } from './raw-material-stock-service.service';

describe('RawMaterialStockServiceService', () => {
  let service: RawMaterialStockServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RawMaterialStockServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
