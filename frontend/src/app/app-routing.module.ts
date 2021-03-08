import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { CourseComponent } from './components/course/course.component';
import { EditCourseComponent } from './components/edit-course/edit-course.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ViewCourseComponent } from './components/view-course/view-course.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: LoginComponent},
  {path: 'home', pathMatch: 'full', component: HomeComponent},
  {path: 'addCourse', pathMatch: 'full', component: CourseComponent},
  {path: 'editCourse', pathMatch: 'full', component: EditCourseComponent},
  {path: 'viewCourse', pathMatch: 'full', component: ViewCourseComponent},
  {path: 'viewCourseDetails/:id', pathMatch: 'full', component: CourseDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
