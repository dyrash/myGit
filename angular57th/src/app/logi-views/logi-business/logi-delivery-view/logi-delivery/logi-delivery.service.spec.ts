import { TestBed } from '@angular/core/testing';

import { LogiDeliveryService } from './logi-delivery.service';

describe('LogiDeliveryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogiDeliveryService = TestBed.get(LogiDeliveryService);
    expect(service).toBeTruthy();
  });
});
