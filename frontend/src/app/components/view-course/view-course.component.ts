import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-view-course',
  templateUrl: './view-course.component.html',
  styleUrls: ['./view-course.component.scss']
})
export class ViewCourseComponent implements OnInit {

  dataSource: any;
  displayedColumns: String[] = ['id', 'title', 'description', 'prerequisite', 'createdOn','view'];

  constructor(private router: Router, private courseService: CourseService) { }

  ngOnInit(): void {
    this.courseService.viewAllCoursesFromRemote().subscribe(
      resp => {this.dataSource = resp;
      console.log(this.dataSource);}
    )
  }

  signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }

  showDetails(element) {
    console.log(element);
    this.courseService.setViewCourse(element);
    this.router.navigate(['/viewCourseDetails', element.courseId]);
  }

}
