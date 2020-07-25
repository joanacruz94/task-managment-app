import { Component, OnInit } from '@angular/core';
import { ROUTES } from './sidebar-routes.config';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit {
  menuItems: Array<object>;
  activeItem: string;
  constructor() {}

  ngOnInit(): void {
    this.menuItems = ROUTES;
    this.activeItem = 'Dashboard';
  }

  changeActive(clickedAnchor: string) {
    this.activeItem = clickedAnchor;
  }
}
