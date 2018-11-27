package com.hackathon.model;

public class Questions {
	private int id;
	private String question;
	private int marks;
	private String level;
	private String op1;
	private String op1Ans;
	private String op2;
	private String op2Ans;
	private String op3;
	private String op3Ans;
	private String op4;
	private String op4Ans;
	private String tech;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp1Ans() {
		return op1Ans;
	}
	public void setOp1Ans(String op1Ans) {
		this.op1Ans = op1Ans;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp2Ans() {
		return op2Ans;
	}
	public void setOp2Ans(String op2Ans) {
		this.op2Ans = op2Ans;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp3Ans() {
		return op3Ans;
	}
	public void setOp3Ans(String op3Ans) {
		this.op3Ans = op3Ans;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public String getOp4Ans() {
		return op4Ans;
	}
	public void setOp4Ans(String op4Ans) {
		this.op4Ans = op4Ans;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	@Override
	public String toString() {
		return "Questions [id=" + id + ", question=" + question + ", marks=" + marks + ", level=" + level + ", op1="
				+ op1 + ", op1Ans=" + op1Ans + ", op2=" + op2 + ", op2Ans=" + op2Ans + ", op3=" + op3 + ", op3Ans="
				+ op3Ans + ", op4=" + op4 + ", op4Ans=" + op4Ans + ", tech=" + tech + "]";
	}
	
	
}
