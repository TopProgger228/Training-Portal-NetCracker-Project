package com.group3.basic.netcracker.backend.course;

import java.sql.Date;

public class Course {
	int id;
	String name;
	String info;
	int userId;
	String skillLevel;
	String learnDirection;
	Date startDate;
	Date endDate;
	int qtyPerWeek;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getLearnDirection() {
		return learnDirection;
	}
	public void setLearnDirection(String learnDirection) {
		this.learnDirection = learnDirection;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getQtyPerWeek() {
		return qtyPerWeek;
	}
	public void setQtyPerWeek(int qtyPerWeek) {
		this.qtyPerWeek = qtyPerWeek;
	}
  @Override
 public String toString() {
	return name + ' ' + skillLevel + ' ' + learnDirection+ ' ' + startDate + " ID: " + id;
	  
  }
}
