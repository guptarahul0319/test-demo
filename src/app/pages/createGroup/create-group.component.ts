import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.scss']
})
export class CreateGroupComponent implements OnInit {
  createGroupForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {

    this.createGroupForm = this.formBuilder.group({
      groupname: ['', Validators.required],
      groupadmin: ['', Validators.required],
      description: ['', Validators.required],
      addmembers: ['', Validators.required]
  });


  }
  createGroup() {
    alert('Coming soon!');
  }
  get createGroupFormControl() { return this.createGroupForm.controls; }


}
