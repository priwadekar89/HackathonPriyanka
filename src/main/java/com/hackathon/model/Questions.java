package com.hackathon.model;

public class Questions {
	private int id;
	private String question;
	private int marks;
	private String op1;
	private String op1valid;
	private String op2;
	private String op2valid;
	private String op3;
	private String op3valid;
	private String op4;
	private String op4valid;
	private String category;
	
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
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp1valid() {
		return op1valid;
	}
	public void setOp1valid(String op1valid) {
		this.op1valid = op1valid;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp2valid() {
		return op2valid;
	}
	public void setOp2valid(String op2valid) {
		this.op2valid = op2valid;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp3valid() {
		return op3valid;
	}
	public void setOp3valid(String op3valid) {
		this.op3valid = op3valid;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public String getOp4valid() {
		return op4valid;
	}
	public void setOp4valid(String op4valid) {
		this.op4valid = op4valid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Questions [id=" + id + ", question=" + question + ", marks=" + marks + ", op1=" + op1 + ", op1valid="
				+ op1valid + ", op2=" + op2 + ", op2valid=" + op2valid + ", op3=" + op3 + ", op3valid=" + op3valid
				+ ", op4=" + op4 + ", op4valid=" + op4valid + ", category=" + category + "]";
	}
}
