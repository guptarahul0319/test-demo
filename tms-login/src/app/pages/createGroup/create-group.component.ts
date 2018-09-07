import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import{EmployeesService} from 'src/app/_services/employees/employees.service';
import {GetAllEmployessService} from 'src/app/_services/getAllEmployees/get-all-employess.service';
import {Group} from 'src/app/_services/group/group.service';
import {CreateGroupService} from 'src/app/_services/createGroup/create-group.service';
import{GetRequestService} from 'src/app/_services/getRequest/get-request.service';
// import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
// import { FormsModule } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.scss'],
  encapsulation: ViewEncapsulation.None 
})
 
export class CreateGroupComponent implements OnInit {

  employees : EmployeesService[];
  userForm: FormGroup;
  submitted = false;
  adminItemList = [];
  statusItemList = [];
  employeeSettings = {};
  statusSettings = {};
  adminSelectedItems = [];
  statusSelectedItems = [];  
  employeeSelectedItems = [];
  employeeItemList = [];
  adminSettings= {};
  groups: {};
  group : Group;
  a= [];
 

  constructor(
    private formBuilder: FormBuilder,
    // private spinnerService: Ng4LoadingSpinnerService,
    private spinner: NgxSpinnerService,
    private route: ActivatedRoute,
    private getAllEmployessService : GetAllEmployessService,
    private getRequestService : GetRequestService,
    private createGroupService: CreateGroupService,
    private router: Router,
  ) {}


  ngOnInit() {

 
    this.userForm = this.formBuilder.group({
      'employeeList': ['', Validators.required],
      'adminList': ['', Validators.required],
      'groupName': ['', Validators.required],
      'groupdescription': ['', Validators.required],
      'statusList': ['', Validators.required]

    });
     
    
    this.spinner.show();
    setTimeout(() => {
    this.getAllEmployessService.getEmployee()
    .subscribe( (employees)=> { 
      // this.spinnerService.hide();
      this.spinner.hide();
      this.employees = employees;

      this.employees.forEach(employee => {
        this.adminItemList.push({
          'id' : employee.empId,
          'itemName' : employee.empName + "-" + employee.empId
        });
      
        this.employeeItemList.push({
          'id' : employee.empId,
          'itemName' : employee.empName + "-" + employee.empId
        });
        
      });
     });
    }, 1000);

     this.statusItemList = [
      { id: 0, itemName : "Active"},
      { id: 1, itemName : "Inactive"}];

     this.employeeSettings = {
      singleSelection: false,
      text: "Select Members",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      badgeShowLimit: 5
    };
    
    this.adminSettings = {singleSelection: true, text: "Select Admin", enableSearchFilter: true };
    this.statusSettings = {singleSelection: true, text: "Select Status"};
    }

    onSubmit() {
        this.submitted = true;
        // stop here if form is invalid
        if (this.userForm.invalid) {
            return;
        }

        this.groups = this.userForm.value;

          
          // this.group.groupName= this.groups[2]['groupName'];

          // console.log((<HTMLInputElement>document.getElementById("groupName")).value);
          
            
          // for(var i=0 ; i< this.groups['employeeList'].length;i++)
          // {
          //   this.group['groupEmpIds'] = this.group['groupEmpIds'] + this.groups['employeeList'][i]['id'] + ",";
          // }
          console.log(this.groups);

          for(var i=0 ; i < this.groups['employeeList'].length; i++)
          {
            this.a[i] = this.groups['employeeList'][i]['id'];
          }
          this.group = new Group(this.a);
          for(var i=0 ; i< this.groups['adminList'].length;i++)
          {
            this.group['adminId']= this.groups['adminList'][i]['id'] ;
          }

          this.group['groupName']= this.groups['groupName'];
          this.group['description']= this.groups['groupdescription'];
          this.group['isArchived']= this.groups['statusList'][0]['id'];
          
          this.createGroupService.createGroup(this.group).subscribe((res)=> {

              this.router.navigateByUrl('/adminConfig');
          });

    }
 }
