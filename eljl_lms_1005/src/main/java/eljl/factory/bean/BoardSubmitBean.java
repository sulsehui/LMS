package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardSubmitBean {
	String teId;
	String teName;
	String stuId;
	String stuName;
	String csCode;
	String csName;
	String opCode;
	String opName;
	String numCode;
	String title;
	String contents;
	int grade;
	String answer;
	String filePath;
	Date createTime;
}
