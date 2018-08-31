import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {GetRequestService} from 'src/app/_services/getRequest/get-request.service';
import {Group} from 'src/app/_services/group/group.service';
import { Subscriber } from 'rxjs';
// import { MemberfetchService } from 'src/app/_services/memberfetch.service';
@Component({
  selector: 'app-admin-config',
  templateUrl: './admin-config.component.html',
  styleUrls: ['./admin-config.component.scss']
})
export class AdminConfigComponent implements OnInit {

  serialNumbero: number;
  edit: number;
  groups : Group[];
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private getRequestService : GetRequestService,
    // private memberFetchService: MemberfetchService
  ) { }

  ngOnInit() {
    
      this.getRequestService.getGroup()
      .subscribe( (groups)=> { 
      this.groups = groups;
     });

  }
}