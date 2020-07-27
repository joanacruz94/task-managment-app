import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "src/app/services/auth.service";
import { RequestService } from "src/app/services/request.service";
import * as moment from "moment";

@Component({
  selector: "app-add-task",
  templateUrl: "./add-task.component.html",
  styleUrls: ["./add-task.component.scss"],
})
export class AddTaskComponent implements OnInit {
  addTaskForm: FormGroup;
  categoryData = [
    { value: "DEVELOPMENT", viewValue: "Development" },
    { value: "GENERAL", viewValue: "General" },
    { value: "FRONT_END", viewValue: "Front end" },
    { value: "BACK_END", viewValue: "Back end" },
    { value: "DEV_OPS", viewValue: "DevOps" },
    { value: "DESIGN", viewValue: "Design" },
    { value: "DATABASE", viewValue: "Database" },
  ];
  urgencyData = [
    { value: "VERY_LOW", viewValue: "Very Low" },
    { value: "LOW", viewValue: "Low" },
    { value: "STANDARD", viewValue: "Standard" },
    { value: "HIGH", viewValue: "High" },
    { value: "CRITICAL", viewValue: "Critical" },
  ];
  projects = null;
  members = null;
  selectedProject;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private requestService: RequestService,
    private dialogRef: MatDialogRef<AddTaskComponent>
  ) {}

  ngOnInit(): void {
    this.fetchData();
    this.addTaskForm = this.fb.group({
      description: ["", [Validators.required]],
      category: ["DEVELOPMENT", [Validators.required]],
      urgency: ["STANDARD", [Validators.required]],
      projectID: [1, [Validators.required]],
      startDate: [""],
      endDate: [""],
      responsibleEmail: [""],
    });
  }

  changeProjectID(value) {
    this.selectedProject = value;
    this.fetchMembersData();
  }

  async fetchData() {
    try {
      const projectsData = await this.requestService.get(
        `project/projectsByUser/${this.authService.getUserID()}`
      );
      this.selectedProject = projectsData[0].id;
      this.projects = projectsData;
      this.fetchMembersData();
    } catch (error) {
      console.log(error);
    }
  }

  async fetchMembersData() {
    try {
      const membersData = await this.requestService.get(
        `project/membersOfProject/${this.selectedProject}`
      );
      this.members = membersData;
    } catch (error) {
      console.log(error);
    }
  }

  closeModal() {
    this.dialogRef.close();
  }

  async submitTask() {
    try {
      const data = this.addTaskForm.value;
      const momentStartDate = new Date(data.startDate);
      const formattedStartDate = moment(momentStartDate).format("YYYY-MM-DD");
      const momentEndDate = new Date(data.endDate);
      const formattedEndDate = moment(momentEndDate).format("YYYY-MM-DD");
      data.startDate = formattedStartDate;
      data.endDate = formattedEndDate;
      data.status = "NEW";
      console.log(data);
      const result = await this.requestService.post("task", data);
      console.log(result);
      this.dialogRef.close();
    } catch (error) {
      console.log(error);
    }
  }
}
