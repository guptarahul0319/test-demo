import { TestBed, inject } from '@angular/core/testing';

import { UpdateStatusService } from './update-status.service';

describe('UpdateStatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UpdateStatusService]
    });
  });

  it('should be created', inject([UpdateStatusService], (service: UpdateStatusService) => {
    expect(service).toBeTruthy();
  }));
});
