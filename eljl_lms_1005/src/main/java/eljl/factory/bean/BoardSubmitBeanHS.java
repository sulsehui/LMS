package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class BoardSubmitBeanHS {
	String mbId;
	String teName;
	String stuId;
	String stuName;
	String csCode;
	String csName;
	String ssCode; //추가
	String ssName; //추가
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
