import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {GetGroupDetailsService} from 'src/app/_services/getGroupDetails/get-group-details.service';
import {Group} from 'src/app/_services/group/group.service';
// import { MemberfetchService } from 'src/app/_services/memberfetch.service';
@Component({
  selector: 'app-admin-config',
  templateUrl: './admin-config.component.html',
  styleUrls: ['./admin-config.component.scss']
})
export class AdminConfigComponent implements OnInit {

  serialNumbero: number;
  edit: number;
  group : Group[];
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private getGroupDetailsService : GetGroupDetailsService,
    // private memberFetchService: MemberfetchService
  ) { }

  ngOnInit() {
      this.group = this.getGroupDetailsService.getGroup();
  }
}