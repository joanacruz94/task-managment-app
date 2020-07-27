import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Inject } from "@angular/core";
import { RequestService } from "src/app/services/request.service";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";

@Component({
  selector: "app-add-member",
  templateUrl: "./add-member.component.html",
  styleUrls: ["./add-member.component.scss"],
})
export class AddMemberComponent implements OnInit {
  horizontalPosition: MatSnackBarHorizontalPosition = "center";
  verticalPosition: MatSnackBarVerticalPosition = "bottom";
  addMemberForm: FormGroup;
  projects = null;
  messageError: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private requestService: RequestService,
    private dialogRef: MatDialogRef<AddMemberComponent>,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.projects = this.data;
    this.addMemberForm = this.fb.group({
      projectID: ["", [Validators.required]],
      userEmail: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"),
        ],
      ],
    });
    this.addMemberForm.valueChanges.subscribe(console.log);
  }

  closeModal() {
    this.dialogRef.close();
  }

  async addMember() {
    try {
      const data = this.addMemberForm.value;
      const result = await this.requestService.post("project/addMember", data);
      if (result.code === 409) {
        this.messageError = result.message;
        this.openSnackBar();
      }
      this.dialogRef.close();
    } catch (error) {
      this.messageError = error;
      this.openSnackBar();
    }
  }

  openSnackBar() {
    this.snackBar.open(this.messageError, "Close", {
      duration: 5000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      panelClass: ["error-snackbar"],
    });
  }
}
