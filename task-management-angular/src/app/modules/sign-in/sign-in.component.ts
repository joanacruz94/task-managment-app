import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "src/app/services/auth.service";
import { Router } from "@angular/router";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from "@angular/material/snack-bar";

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {
  horizontalPosition: MatSnackBarHorizontalPosition = "center";
  verticalPosition: MatSnackBarVerticalPosition = "bottom";
  loginForm: FormGroup;
  hasError: boolean;
  messageError: string;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<SignInComponent>
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [
        "",
        [
          Validators.required,
          Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"),
        ],
      ],
      password: ["", [Validators.required, Validators.minLength(7)]],
    });
    this.loginForm.valueChanges.subscribe(console.log);
  }

  async submitForm() {
    try {
      const result = await this.authService.login(this.loginForm.value);
      this.dialogRef.close();
      this.router.navigate(["tasks"]);
    } catch (error) {
      this.hasError = true;
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

  get email() {
    return this.loginForm.get("email");
  }

  get password() {
    return this.loginForm.get("password");
  }
}
