import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { DefaultComponent } from "./layouts/default/default.component";
import { DashboardComponent } from "./modules/dashboard/dashboard.component";
import { MemberComponent } from "./modules/member/member.component";
import { NotificationComponent } from "./modules/notification/notification.component";
import { ProjectComponent } from "./modules/project/project.component";
import { TaskComponent } from "./modules/task/task.component";
import { HomeComponent } from "./layouts/home/home.component";
import { AuthGuard } from "./guards/auth-guard.service";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
  },
  {
    path: "",
    component: DefaultComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: "dashboard",
        component: DashboardComponent,
      },
      {
        path: "tasks",
        component: TaskComponent,
      },
      {
        path: "projects",
        component: ProjectComponent,
      },
      {
        path: "notifications",
        component: NotificationComponent,
      },
      {
        path: "members",
        component: MemberComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
