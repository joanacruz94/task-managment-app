import { Component, OnInit } from "@angular/core";
import { AuthService } from "src/app/services/auth.service";
import { RequestService } from "src/app/services/request.service";

@Component({
  selector: "app-notification",
  templateUrl: "./notification.component.html",
  styleUrls: ["./notification.component.scss"],
})
export class NotificationComponent implements OnInit {
  notifications = null;

  constructor(
    private authService: AuthService,
    private requestService: RequestService
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  async fetchData() {
    try {
      const result = await this.requestService.get(
        `notification/notificationsByUser/${this.authService.getUserID()}`
      );
      this.notifications = result;
      console.log(this.notifications);
    } catch (error) {
      console.log(error);
    }
  }
}
