import { TestBed, inject } from '@angular/core/testing';

import { SteakService } from './steak.service';

describe('SteakService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SteakService]
    });
  });

  it('should be created', inject([SteakService], (service: SteakService) => {
    expect(service).toBeTruthy();
  }));
});
