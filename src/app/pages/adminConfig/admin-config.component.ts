import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit ,ChangeDetectorRef} from '@angular/core';
import { GetGroupsService } from 'src/app/_services/getGroups/get-groups.service';
import {Group} from 'src/app/_modal/group.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { GetGroupService } from '../../_services/getGroup/get-group.service';
import { UpdateGroupService } from '../../_services/updateGroup/update-group.service';
import { NotifierService } from 'angular-notifier';


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
  v={};
  notifier: NotifierService;
  total= 10;
  pageList:number[];
  count;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private updateGroupService : UpdateGroupService,
    private spinner: NgxSpinnerService,
    private notifiers: NotifierService,
    private getGroupsService : GetGroupsService,
    private getGroupService : GetGroupService,
    private changeDetectorRef : ChangeDetectorRef
  ) {
    this.notifier = notifiers;
   }

  ngOnInit() {
    
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }

    this.total;
    
    this.getGroupsService.getCount().subscribe( (data)=>{

      this.count=(data%5==0)?~~(data/5):(~~(data/5)+1);

      this.pageList=new Array(this.count);

        for(let i:number=0 ; i < this.count ; i++){
              this.pageList[i]=i+1;
            }
      });

      this.pageNo(1);
   }

   addGroup() {

     this.router.navigateByUrl('group');
   }

   pageNo(page:number) { 
       this.spinner.show();
       setTimeout(() => {

        var a = <HTMLElement>document.getElementsByClassName("selected")[1];
        a.style.backgroundColor = "#008B8B";

        this.getGroupsService.getGroup( page )
             .subscribe( (groups)=> { 
                     this.spinner.hide();
                     this.groups = groups;
                });
       }, 500);
    }

  deleteGroup(group : Group) {

    this.updateGroupService.deleteGroup(group['groupId'])
    .subscribe( (res)=> { 

      console.log(res);

      if(res['Success'] === 'false')
      {
        this.showNotification( 'info', res['Messsage'] );
      }
      else{

        this.showNotification( 'info', res['Messsage'] );

        setTimeout(() => {

          // this.ngOnInit();
        
        },2000);
      }
    });
  }
  public showNotification( type: string, message: string ): void {

    console.log( message );
    console.log( type )
    this.notifier.notify( type, message );
  }
 }