import { TestBed } from '@angular/core/testing';

import { AccService } from './acc.service';

describe('AccService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccService = TestBed.get(AccService);
    expect(service).toBeTruthy();
  });
});
