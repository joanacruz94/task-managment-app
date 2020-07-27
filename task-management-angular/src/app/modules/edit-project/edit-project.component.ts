import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Inject } from "@angular/core";
import { RequestService } from "src/app/services/request.service";

@Component({
  selector: "app-edit-project",
  templateUrl: "./edit-project.component.html",
  styleUrls: ["./edit-project.component.scss"],
})
export class EditProjectComponent implements OnInit {
  editProjectForm: FormGroup;
  projectID;
  description;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private requestService: RequestService,
    private dialogRef: MatDialogRef<EditProjectComponent>
  ) {}

  ngOnInit(): void {
    this.projectID = this.data.id;
    this.description = this.data.description;
    this.editProjectForm = this.fb.group({
      description: [this.description],
    });
    this.editProjectForm.valueChanges.subscribe(console.log);
  }

  closeModal() {
    this.dialogRef.close();
  }

  async editProject() {
    try {
      const data = this.editProjectForm.value;
      data.projectID = this.projectID;
      const result = await this.requestService.patch(
        "project/updateDescription",
        data
      );
      this.dialogRef.close();
    } catch (error) {
      console.log(error);
    }
  }
}
