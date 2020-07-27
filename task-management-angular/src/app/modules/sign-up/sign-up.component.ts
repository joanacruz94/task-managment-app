import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { RequestService } from "src/app/services/request.service";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";

@Component({
  selector: "app-sign-up",
  templateUrl: "./sign-up.component.html",
  styleUrls: ["./sign-up.component.scss"],
})
export class SignUpComponent implements OnInit {
  horizontalPosition: MatSnackBarHorizontalPosition = "center";
  verticalPosition: MatSnackBarVerticalPosition = "bottom";
  signUpForm: FormGroup;
  messageError: string;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<SignUpComponent>,
    private requestService: RequestService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      name: ["", [Validators.required]],
      email: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"),
        ],
      ],
      password: ["", [Validators.required, Validators.minLength(7)]],
    });
    this.signUpForm.valueChanges.subscribe(console.log);
  }

  async signUp() {
    try {
      const result = await this.requestService.post(
        "auth/sign-up",
        this.signUpForm.value
      );
      console.log(result, "RESULT");
      this.dialogRef.close();
    } catch (error) {
      console.log(error, "ERROR");
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

  closeModal() {
    this.dialogRef.close();
  }

  get firstName() {
    return this.signUpForm.get("firstName");
  }

  get lastName() {
    return this.signUpForm.get("lastName");
  }

  get email() {
    return this.signUpForm.get("email");
  }

  get password() {
    return this.signUpForm.get("password");
  }
}
