import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "src/app/services/auth.service";
import { RequestService } from "src/app/services/request.service";

@Component({
  selector: "app-add-project",
  templateUrl: "./add-project.component.html",
  styleUrls: ["./add-project.component.scss"],
})
export class AddProjectComponent implements OnInit {
  addProjectForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private requestService: RequestService,
    private dialogRef: MatDialogRef<AddProjectComponent>
  ) {}

  ngOnInit(): void {
    this.addProjectForm = this.fb.group({
      name: ["", [Validators.required]],
      description: [""],
    });
    this.addProjectForm.valueChanges.subscribe(console.log);
  }

  closeModal() {
    this.dialogRef.close();
  }

  async submitProject() {
    try {
      const data = this.addProjectForm.value;
      data.userID = this.authService.getUserID();
      const result = await this.requestService.post("project", data);
      this.dialogRef.close();
    } catch (error) {
      console.log(error);
    }
  }
}
