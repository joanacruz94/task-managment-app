import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { SignInComponent } from "src/app/modules/sign-in/sign-in.component";
import { SignUpComponent } from "src/app/modules/sign-up/sign-up.component";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  constructor(private dialog: MatDialog) {}

  ngOnInit(): void {}

  openLoginModal() {
    const dialogRef = this.dialog.open(SignInComponent, {
      data: {},
      height: "380px",
      width: "550px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log("closed");
    });
  }

  openSignUpModal() {
    const dialogRef = this.dialog.open(SignUpComponent, {
      data: {},
      height: "470px",
      width: "550px",
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log("closed");
    });
  }
}
