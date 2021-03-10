import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/Models/Course';
import { Skill } from 'src/app/Models/Skill';
import { TrainingMaterial } from 'src/app/Models/TrainingMaterial';
import { CourseService } from 'src/app/services/course.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {


  course: Course = new Course();

  skill: Skill = new Skill();

  trainingMaterial: TrainingMaterial = new TrainingMaterial();

  message: String = "";

  files: any;

  fileAttr = 'Choose File';

  blob: Blob;

  constructor(private router: Router, private courseService: CourseService, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    console.log(auth2.currentUser)
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
    this.router.navigate(['']);
  }

  OnAddCourse() {
    console.log(this.course);
    // console.log(JSON.parse(this.loginService.getUserId()).email);

    if(this.course.courseName != "" && this.course.courseName != null && this.course.courseDescription != "" && this.course.courseDescription != null && this.course.prerequisite != "" && this.course.prerequisite != null && this.skill.skillName != "" && this.skill.skillName != null){
    this.loginService.getUserByEmailFromRemote(JSON.parse(this.loginService.getUserId()).email).subscribe(
      resp => {console.log(resp.userId);
              this.course.userId = resp.userId;
              console.log(this.course);
              this.courseService.addCourseFromRemote(this.course).subscribe(
                resp => {console.log(this.course);}
              );
            }
    )

    setTimeout(() => {
      this.courseService.getCourseWithHighestId().subscribe(
        resp => {
          this.skill.courseId = resp.courseId;
          this.trainingMaterial.courseId = resp.courseId;
          console.log(this.trainingMaterial);

          console.log("RESP", resp);
          this.courseService.addSkillFromRemote(this.skill).subscribe(
          resp => {console.log(this.course);}
          );
        }
      )

      setTimeout(() => {
        this.courseService.addTrainingMaterialfromRemote(this.trainingMaterial.courseId, this.trainingMaterial.fileType, this.trainingMaterial.fileName, this.files).subscribe(
         resp => {console.log(resp);}
       )
       console.log(this.trainingMaterial);
       this.message = "Course added successfully!!";
       }, 3000);
     }, 3000);

     


    // this.courseService.getCourseWithHighestId().subscribe(
    //   resp => {
    //     this.skill.courseId = resp.courseId + 1;

    //     console.log("RESP", resp);
    //     this.courseService.addSkillFromRemote(this.skill).subscribe(
    //     resp => {console.log(this.course);}
    //     )
    //   }
    // )
     
    //  this.course.courseName = "";
    //  this.course.courseDescription = "";
    //  this.course.prerequisite = "";
    //  this.skill.skillName = "";
    console.log("Skillsslsllslslsls: ",this.skill); 
    }
    else{
      alert("Please fill all the fields!");
    }   
  }

  viewAllCourses() {
    
  }

  onFileChanged(event){
    // this.files = event.target.files[0];
    this.files = event;
    console.log(this.files);
    console.log(this.files.type);
    this.trainingMaterial.fileName = this.files.name;
    this.fileAttr = this.trainingMaterial.fileName;
    this.trainingMaterial.fileType = this.files.type;
    // this.blob = new Blob(this.files, {});
    // this.trainingMaterial.fileData = this.files;
    // this.blob = new Blob(this.files);
    // const uploadFileData = new FormData();
    // uploadFileData.append('file', this.files, this.files.name);
  }
  

}
