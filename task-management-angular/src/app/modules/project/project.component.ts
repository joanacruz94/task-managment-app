import { Component, OnInit } from "@angular/core";
import { RequestService } from "src/app/services/request.service";
import { AuthService } from "src/app/services/auth.service";
import { MatDialog } from "@angular/material/dialog";
import { AddProjectComponent } from "src/app/modules/add-project/add-project.component";
import { AddMemberComponent } from "src/app/modules/add-member/add-member.component";
import { EditProjectComponent } from "src/app/modules/edit-project/edit-project.component";

@Component({
  selector: "app-project",
  templateUrl: "./project.component.html",
  styleUrls: ["./project.component.scss"],
})
export class ProjectComponent implements OnInit {
  projects = null;

  constructor(
    private requestService: RequestService,
    private authService: AuthService,
    private dialogNewProject: MatDialog,
    private dialogAddMember: MatDialog,
    private dialogEditProject: MatDialog
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  async fetchData() {
    try {
      const result = await this.requestService.get(
        `project/projectsByUser/${this.authService.getUserID()}`
      );
      this.projects = result;
    } catch (error) {
      console.log(error);
    }
  }

  openNewProjectModal() {
    const dialogRef = this.dialogNewProject.open(AddProjectComponent, {
      height: "500px",
      width: "500px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.fetchData();
    });
  }

  openAddMemberModal() {
    const dialogRef = this.dialogAddMember.open(AddMemberComponent, {
      data: this.projects,
      height: "350px",
      width: "500px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.fetchData();
    });
  }

  openEditProjectModal(id, description) {
    const dialogRef = this.dialogEditProject.open(EditProjectComponent, {
      data: {
        id,
        description,
      },
      height: "400px",
      width: "500px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      this.fetchData();
    });
  }

  async deleteProject(id) {
    try {
      const result = await this.requestService.delete(`project/${id}`);
      this.projects = this.projects.filter((project) => project.id !== id);
    } catch (error) {
      console.log(error);
    }
  }
}
