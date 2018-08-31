import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Group } from 'src/app/_services/group/group.service';
import {GetRequestService} from 'src/app/_services/getRequest/get-request.service';

@Component({
  selector: 'app-update-group',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.scss']
})
export class UpdateGroupComponent implements OnInit {

  group: Group;
  constructor(
    private route: ActivatedRoute,
    private getRequestService: GetRequestService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getSelectedGroup();
  }

  getSelectedGroup(): void {
    const serialNumber = +this.route.snapshot.paramMap.get('serialNumber');
      this.group = this.getRequestService.getSelectedGroup(serialNumber);
  }

}
