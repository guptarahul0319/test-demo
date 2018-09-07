import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { NgxSpinnerService } from 'ngx-spinner';

import { Group } from 'src/app/_services/group/group.service';
import {GetRequestService} from 'src/app/_services/getRequest/get-request.service';
import {GetAllEmployessService} from 'src/app/_services/getAllEmployees/get-all-employess.service';
import{EmployeesService} from 'src/app/_services/employees/employees.service';

@Component({
  selector: 'app-update-group',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.scss']
})
export class UpdateGroupComponent implements OnInit {

  group: Group;
  employees : EmployeesService[];
  adminItemList = [];
  adminSelectedItems = [];
  adminSettings= {};
  statusSettings = {};
  employeeSettings = {};
  employeeSelectedItems = [];
  employeeItemList = [];
  statusItemList = [];
  statusSelectedItems = []; 

  constructor(
    private route: ActivatedRoute,
    private getRequestService: GetRequestService,
    private spinner: NgxSpinnerService,
    private getAllEmployessService : GetAllEmployessService,
    private location: Location
  ) { }

  ngOnInit() {
    
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 1000);

    this.group = this.getRequestService.getSelectedGroup();
    console.log(this.group);

    this.adminSelectedItems = [{
      id: this.group.adminId,
      itemName: this.group.adminName + "-" + this.group.adminId
    }];

    if(this.group.isArchived === '0'){
    this.statusSelectedItems = [{
      id: 0,
      itemName: "Active"
    }]
  }
  else{
    this.statusSelectedItems = [{
      id: 1,
      itemName: "Inactive"
    }]
  }
    this.getAllEmployessService.getEmployee()
    .subscribe( (employees)=> { 
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
     this.statusItemList = [
      { id: 0, itemName : "Active"},
      { id: 1, itemName : "Inactive"}];

    this.adminSettings = {singleSelection: true, text: "Select Admin", enableSearchFilter: true };

     this.employeeSettings = {
      singleSelection: false,
      text: "Select Members",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      badgeShowLimit: 5
    };
    
    this.statusSettings = {singleSelection: true, text: "Select Status"};

  }
  
}
