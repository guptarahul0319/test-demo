import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class Group {
  adminId: number;
  adminName: String;
  groupEmpIds: String;
  groupName: String;
  description: String;
  isArchived: boolean;
}
