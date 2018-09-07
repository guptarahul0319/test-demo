import { TestBed, inject } from '@angular/core/testing';

import { GetRequestService } from './get-request.service';

describe('GetRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetRequestService]
    });
  });

  it('should be created', inject([GetRequestService], (service: GetRequestService) => {
    expect(service).toBeTruthy();
  }));
});
