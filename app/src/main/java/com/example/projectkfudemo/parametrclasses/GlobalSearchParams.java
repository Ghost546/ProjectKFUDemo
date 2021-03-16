package com.example.projectkfudemo.parametrclasses;

import android.util.Log;

public class GlobalSearchParams {
    String declarerFIO; //ФИО заявителя

    Integer cod;    //номер заявки

    String date1;   //дата подачи заявки с
    String date2;   //дата подачи заявки по

    Integer regType;    //тип заявки

    Integer statusId;   //статус заявки

    Integer regUserId;  //id зарегистрировавшего заявку

    Integer workerId;   //id исполнителя

    String text;    //текст заявки

    Integer techGroup;  //id отдела исполнителей

    String office;  //подразделение заявителя

    String address; //местонахождение заявителя

    String roomNum; //номер комнаты(кабинета)

    public String getDeclarerFIO() {
        return declarerFIO;
    }

    public void setDeclarerFIO(String declarerFIO) {
        this.declarerFIO = declarerFIO;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Integer getRegType() {
        return regType;
    }

    public void setRegType(Integer regType) {
        this.regType = regType;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(Integer regUserId) {
        this.regUserId = regUserId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTechGroup() {
        return techGroup;
    }

    public void setTechGroup(Integer techGroup) {
        this.techGroup = techGroup;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
