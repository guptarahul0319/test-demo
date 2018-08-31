import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input() public isUserLoggedIn: boolean;

  constructor(private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
  }
logOut() {
  sessionStorage.removeItem('isLoggedIn');
  this.router.navigateByUrl('/login');
}

adminConfiguration() {
  this.router.navigateByUrl('/adminConfig');
}

}
