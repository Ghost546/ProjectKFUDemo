package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeInfo {
    @SerializedName("employee_id")
    @Expose
    private int employeeId;
    @SerializedName("post_name")
    @Expose
    private String postName;
    @SerializedName("subdivision")
    @Expose
    private String subdivision;
    @SerializedName("is_teacher")
    @Expose
    private boolean isTeacher;
    @SerializedName("photo_id")
    @Expose
    private int photoId;
    @SerializedName("photo")
    @Expose
    private String photo;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
