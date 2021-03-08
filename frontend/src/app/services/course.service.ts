import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../Models/Course';
import { Feedback } from '../Models/Feedback';
import { Skill } from '../Models/Skill';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  editCourse: Course;
  viewCourse: Course;

  BASE_URL = "http://localhost:8080/";
  ADD_COURSE = "course/addCourse/";
  GET_ALL_COURSES = "course/getAllCourses/";
  EDIT_COURSE = "course/editCourses/";
  GET_COURSE_BY_USER_ID = "course/getCourseByUserId/";
  DELETE_COURSE = "course/deleteCourse/";
  ADD_SKILL = "skill/addSkills/";
  GET_COURSE_WITH_HIGHEST_ID = "course/getCourseWithHighestId/";
  GET_SKILLS = "skill/getSkills/";
  GET_FEEDBACK_BY_COURSE_ID = "feedback/getFeedbacksByCourseId/";
  ADD_FEEDBACK = "feedback/addFeedback/";
  ADD_MATERIAL = "material/addMaterial/";
  GET_MATERIAL_BY_COURSE_ID = "material/getMaterial/";

  constructor(private http: HttpClient) { }

  public addCourseFromRemote(course: Course): Observable<any>{
    return this.http.post(this.BASE_URL + this.ADD_COURSE, course);
  }

  public viewAllCoursesFromRemote(): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_ALL_COURSES);
  }

  public setEditCourse(course: Course){
    this.editCourse = course;
  }

  public getEditCourse(){
    return this.editCourse;
  }

  public setViewCourse(course: Course){
    this.viewCourse = course;
  }

  public getViewCourse(){
    return this.viewCourse;
  }

  public editDetails(course: Course): Observable<any>{
    return this.http.put(this.BASE_URL + this.EDIT_COURSE, course);
  }

  public getCourseByUserIdFromRemote(id: Number): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_COURSE_BY_USER_ID + id);
  }

  public deleteCourse(courseId: Number): Observable<any>{
    return this.http.delete(this.BASE_URL + this.DELETE_COURSE + courseId);
  }

  public addSkillFromRemote(skill: Skill): Observable<any>{
    return this.http.post(this.BASE_URL + this.ADD_SKILL, skill);
  }

  public getCourseWithHighestId(): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_COURSE_WITH_HIGHEST_ID);
  }

  public getSkillsFromRemote(courseId: Number): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_SKILLS + courseId);
  }

  public addFeedBackFromRemote(feedback: Feedback): Observable<any>{
    return this.http.post(this.BASE_URL + this.ADD_FEEDBACK, feedback);
  }

  public getFeedbackByCourseIdFromRemote(courseId: Number): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_FEEDBACK_BY_COURSE_ID + courseId);
  }

  public addTrainingMaterialfromRemote(courseId: Number, fileType: string, fileName: string, file: File): Observable<any>{
    const formData: FormData = new FormData();
    console.log(courseId);
    console.log(fileName);
    formData.append('file', file);
    formData.append('fileType', fileType);
    formData.append('courseId', courseId.toString());
    formData.append('fileName', fileName);
    return this.http.post(this.BASE_URL + this.ADD_MATERIAL, formData, {responseType: 'text'});
  }

  public getTrainingMaterial(courseId: Number): Observable<any>{
    return this.http.get(this.BASE_URL + this.GET_MATERIAL_BY_COURSE_ID + courseId);
  }

}
