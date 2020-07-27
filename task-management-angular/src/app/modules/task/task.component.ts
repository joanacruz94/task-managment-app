import { Component, OnInit } from "@angular/core";
import { RequestService } from "src/app/services/request.service";
import { AuthService } from "src/app/services/auth.service";
import { MatDialog } from "@angular/material/dialog";
import { AddTaskComponent } from "../add-task/add-task.component";
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from "@angular/cdk/drag-drop";

@Component({
  selector: "app-task",
  templateUrl: "./task.component.html",
  styleUrls: ["./task.component.scss"],
})
export class TaskComponent implements OnInit {
  tasks = null;
  tasksNew = null;
  tasksProgress = null;
  tasksDone = null;
  constructor(
    private requestService: RequestService,
    private authService: AuthService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    console.log("init");
    this.fetchData();
  }

  ngOnChanges(): void {
    console.log("changes");
  }

  async fetchData() {
    try {
      const result = await this.requestService.get(
        `task/tasksUser/${this.authService.getUserID()}`
      );
      this.tasks = result;
      console.log(this.tasks);
      this.tasksNew = this.tasks.filter((task) => task.status === "NEW");
      this.tasksProgress = this.tasks.filter(
        (task) => task.status === "IN_PROGRESS"
      );
      this.tasksDone = this.tasks.filter((task) => task.status === "DONE");
    } catch (error) {
      console.log(error);
    }
  }

  openAddTaskModal() {
    const dialogRef = this.dialog.open(AddTaskComponent, {
      data: {},
      height: "700px",
      width: "600px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.fetchData();
    });
  }

  async updateStatus(data) {
    try {
      const result = await this.requestService.patch("task/updateStatus", data);
    } catch (error) {
      console.log(error);
    }
  }

  async deleteTask(id, status) {
    try {
      const result = await this.requestService.delete(`task/${id}`);
      if (status === "NEW") {
        this.tasksNew = this.tasksNew.filter((task) => task.id !== id);
      } else if (status === "IN_PROGRESS") {
        this.tasksProgress = this.tasksProgress.filter(
          (task) => task.id !== id
        );
      } else {
        this.tasksDone = this.tasksDone.filter((task) => task.id !== id);
      }
    } catch (error) {
      console.log(error);
    }
  }

  onDrop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      console.log(event.previousContainer);
      console.log(event.item);
      console.log(event.previousContainer.data, "previous");
      console.log(event.container.data, "data");
      console.log(event.previousIndex, "index previous");
      console.log(event.currentIndex, "index current");

      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );

      let taskID;
      let element;
      let status;

      if (event.container.id === "new") {
        element = this.tasksNew[event.currentIndex];
        status = "NEW";
      } else if (event.container.id === "progress") {
        element = this.tasksProgress[event.currentIndex];
        status = "IN_PROGRESS";
      } else if (event.container.id === "done") {
        element = this.tasksDone[event.currentIndex];
        status = "DONE";
      }

      taskID = element.id;
      this.updateStatus({ taskID, status });
    }
  }
}
