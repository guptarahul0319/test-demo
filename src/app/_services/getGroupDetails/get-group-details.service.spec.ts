import { TestBed, inject } from '@angular/core/testing';

import { GetGroupDetailsService } from './get-group-details.service';

describe('GetGroupDetailsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetGroupDetailsService]
    });
  });

  it('should be created', inject([GetGroupDetailsService], (service: GetGroupDetailsService) => {
    expect(service).toBeTruthy();
  }));
});
