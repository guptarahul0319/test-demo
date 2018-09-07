import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit ,ChangeDetectorRef} from '@angular/core';
import {GetRequestService} from 'src/app/_services/getRequest/get-request.service';
import {Group} from 'src/app/_services/group/group.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-admin-config',
  templateUrl: './admin-config.component.html',
  styleUrls: ['./admin-config.component.scss']
})
export class AdminConfigComponent implements OnInit {

  serialNumber: number;
  edit: number;
  groups : Group[];
  group : Group;
  counter = 0;
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private getRequestService : GetRequestService,
    private changeDetectorRef : ChangeDetectorRef
    // private memberFetchService: MemberfetchService
  ) { }

  ngOnInit() {
    
      this.spinner.show();
      setTimeout(() => {

          this.getRequestService.getGroup()
           .subscribe( (groups)=> { 
                this.groups = groups;
                this.spinner.hide();
        });
      }, 1000);
    }

    updateGroup(group) {

      this.getRequestService.setSelectedGroup(group);
      this.router.navigateByUrl('group/'+group.serialNumber+"/edit");
     }

     addGroup() {
      this.router.navigateByUrl('group');
    }
 }