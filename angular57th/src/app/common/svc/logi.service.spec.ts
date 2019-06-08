import { TestBed } from '@angular/core/testing';

import { LogiService } from './logi.service';

describe('LogiService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LogiService = TestBed.get(LogiService);
    expect(service).toBeTruthy();
  });
});
