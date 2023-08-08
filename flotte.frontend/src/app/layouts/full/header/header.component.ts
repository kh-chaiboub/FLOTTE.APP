import {
  Component,
  Output,
  EventEmitter,
  Input,
  ViewEncapsulation,
} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {SercurityService} from "../../../core/services/sercurity.service";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent {
  @Input() showToggle = true;
  @Input() toggleChecked = false;
  @Output() toggleMobileNav = new EventEmitter<void>();
  @Output() toggleMobileFilterNav = new EventEmitter<void>();
  @Output() toggleCollapsed = new EventEmitter<void>();
  loginTemplate: any;
  private loged: any;
  userProfile: any;
  loggedIn: boolean | undefined;
  showFiller = false;

  constructor(public dialog: MatDialog,public securityService:SercurityService) {}
  async ngOnInit() {
    this.loggedIn = await this.securityService.kcService.isLoggedIn();
    if (this.loggedIn) {
      await this.loadUserProfile();
    }
  }
  async loadUserProfile() {
    try {
      this.userProfile = await this.securityService.kcService.loadUserProfile();
    } catch (error) {
      console.error('Failed to load user profile', error);
    }
  }
  getUserFullName() {
    if (this.userProfile) {
      return `${this.userProfile.username}`;
    }
    return '';
  }
  onLogout() {
    this.securityService.kcService.logout(window.location.origin)

  }
  public async getToken() {
  }

  async login(){
    await this.securityService.kcService.login({
      redirectUri: window.location.origin
    })

  }
}
