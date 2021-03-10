import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Course } from 'src/app/Models/Course';
import { Feedback } from 'src/app/Models/Feedback';
import { Skill } from 'src/app/Models/Skill';
import { TrainingMaterial } from 'src/app/Models/TrainingMaterial';
import { User } from 'src/app/Models/User';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';
import { UploadPopUpComponent } from '../upload-pop-up/upload-pop-up.component';

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
  allPreviousVersions: TrainingMaterial[];
  panelOpenState = false;
  flag = 0;
  loggedUser: User;
  isDisabled = false;

  constructor(private router: Router, private courseService: CourseService, public dialog: MatDialog, private loginService: LoginService) { }

  ngOnInit(): void {
    this.viewCourse = this.courseService.getViewCourse();
    this.courseService.setCourseId(this.viewCourse.courseId);
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
        if(this.trainingMaterial[0] == null){
          this.isDisabled = true;
        }
      console.log(resp);}
    );

    this.courseService.getPreviousVersionTrainingMaterialFromRemote(this.viewCourse.courseId).subscribe(
      resp => {this.allPreviousVersions = resp;
        console.log(resp);}
    );

    this.loginService.getUserByEmailFromRemote(JSON.parse(this.loginService.getUserId()).email).subscribe(
      resp => {
        if(resp.userId === this.viewCourse.userId){
          this.flag = 1;
        }
    }
    );

    this.loggedUser = JSON.parse(this.loginService.getUserId());
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

  openDialog(): void {

    const dialogRef = this.dialog.open(UploadPopUpComponent, {
      width: '300px',
      // data: {courseName: this.courseName, courseDescription: this.courseDescription}
    });
  
    // this.courseService.setEditCourse(course);
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.courseName = result;
    });
  }

  deleteMaterial(){
    console.log(this.viewCourse);
    this.courseService.deleteMaterialFromRemote(this.viewCourse.courseId).subscribe(
      resp => {console.log("Deleted the material!");}
    );

  }


}
