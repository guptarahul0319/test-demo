import { TestBed, inject } from '@angular/core/testing';

import { MemberfetchService } from './memberfetch.service';

describe('MemberfetchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MemberfetchService]
    });
  });

  it('should be created', inject([MemberfetchService], (service: MemberfetchService) => {
    expect(service).toBeTruthy();
  }));
});
