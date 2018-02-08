import { TestBed, inject } from '@angular/core/testing';

import { BunService } from './bun.service';

describe('BunService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BunService]
    });
  });

  it('should be created', inject([BunService], (service: BunService) => {
    expect(service).toBeTruthy();
  }));
});
