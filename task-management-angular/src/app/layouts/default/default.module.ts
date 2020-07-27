import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DefaultComponent } from "./default.component";
import { TaskComponent } from "src/app/modules/task/task.component";
import { ProjectComponent } from "src/app/modules/project/project.component";
import { AddTaskComponent } from "src/app/modules/add-task/add-task.component";
import { NotificationComponent } from "src/app/modules/notification/notification.component";
import { AddProjectComponent } from "src/app/modules/add-project/add-project.component";
import { AddMemberComponent } from "src/app/modules/add-member/add-member.component";
import { EditProjectComponent } from "src/app/modules/edit-project/edit-project.component";
import { SharedModule } from "src/app/shared/shared.module";
import { RouterModule } from "@angular/router";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatDividerModule } from "@angular/material/divider";
import { MatCardModule } from "@angular/material/card";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatTableModule } from "@angular/material/table";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { FlexLayoutModule } from "@angular/flex-layout";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";
import { MatInputModule } from "@angular/material/input";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { MatSelectModule } from "@angular/material/select";
import { MatTooltipModule } from "@angular/material/tooltip";
import { DragDropModule } from "@angular/cdk/drag-drop";
import { MatSnackBarModule } from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    DefaultComponent,
    TaskComponent,
    NotificationComponent,
    ProjectComponent,
    AddTaskComponent,
    AddProjectComponent,
    AddMemberComponent,
    EditProjectComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    SharedModule,
    MatSidenavModule,
    MatDividerModule,
    FlexLayoutModule,
    MatCardModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatTooltipModule,
    DragDropModule,
    MatSnackBarModule,
  ],
})
export class DefaultModule {}
