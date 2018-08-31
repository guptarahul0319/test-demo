import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class Group {
  serialNumber: number;
  adminId: number;
  adminName: string;
  groupEmpIds: string;
  GroupName: string;
  description: string;
  isArchived: boolean;
}
