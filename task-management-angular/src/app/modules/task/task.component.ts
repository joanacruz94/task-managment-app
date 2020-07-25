import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/services/request.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss'],
})
export class TaskComponent implements OnInit {
  tasks;
  tasksNew;
  tasksProgress;
  tasksDone;
  constructor(
    private requestService: RequestService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  async fetchData() {
    try {
      const result = await this.requestService.get(
        `task/tasksUser/${this.authService.getUserID()}`
      );
      this.tasks = result;
      this.tasksNew = this.tasks.filter((task) => task.status === 'NEW');
      this.tasksProgress = this.tasks.filter(
        (task) => task.status === 'IN_PROGRESS'
      );
      this.tasksDone = this.tasks.filter((task) => task.status === 'DONE');
    } catch (error) {
      console.log(error);
    }
  }
}
