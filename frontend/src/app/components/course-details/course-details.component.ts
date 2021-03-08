import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/Models/Course';
import { Feedback } from 'src/app/Models/Feedback';
import { Skill } from 'src/app/Models/Skill';
import { TrainingMaterial } from 'src/app/Models/TrainingMaterial';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  viewCourse: Course;
  skill: Skill[];
  feedback: Feedback[];
  newFeedback: Feedback = new Feedback();
  trainingMaterial: TrainingMaterial;

  constructor(private router: Router, private courseService: CourseService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.viewCourse = this.courseService.getViewCourse();
    console.log(this.viewCourse);

    this.newFeedback.courseId = this.viewCourse.courseId;

    this.courseService.getSkillsFromRemote(this.viewCourse.courseId).subscribe(
      resp => {this.skill = resp;
      console.log(resp)}
    );

    this.courseService.getFeedbackByCourseIdFromRemote(this.viewCourse.courseId).subscribe(
      resp => {this.feedback = resp;
      console.log(resp);}
    );

    this.courseService.getTrainingMaterial(this.viewCourse.courseId).subscribe(
      resp => {this.trainingMaterial = resp;
      console.log(resp);}
    );
  }

  signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }

  // console.log(viewCourse);

  onAddFeedBack() {
    this.newFeedback.participantName = JSON.parse(this.loginService.getUserId()).firstName;
    this.newFeedback.createdOn = new Date;
    if(this.newFeedback.feedbackText != "" && this.newFeedback.feedbackText != null){
    this.courseService.addFeedBackFromRemote(this.newFeedback).subscribe(
      resp => {console.log(resp);
      this.feedback.push(resp);}
    )
    this.newFeedback.feedbackText = "";
    }
    else{
      alert("You cannot add a blank feedback!!");
    }
  }

  base64ToArrayBuffer(base64) {
    const binaryString = window.atob(base64);
    const len = binaryString.length;
    const bytes = new Uint8Array(len);

    for (let i = 0; i < len; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }

    return bytes.buffer;
  }

  downloadFile(data, fileType) {
    console.log(this.trainingMaterial[0]);
    const byteArray = this.base64ToArrayBuffer(data);
    console.log(data);
    const blob = new Blob([byteArray], { type: fileType });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }

}
