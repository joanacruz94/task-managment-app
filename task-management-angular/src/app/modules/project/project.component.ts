import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/services/request.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
})
export class ProjectComponent implements OnInit {
  projects;
  userID;

  constructor(
    private requestService: RequestService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.fetchData();
    this.userID = this.authService.getUserID();
  }

  async fetchData() {
    try {
      const result = await this.requestService.get(
        `project/projectsByUser/${this.userID}`
      );
      this.projects = result;
      console.log(this.projects);
    } catch (error) {
      console.log(error);
    }
  }
}
