package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class StuManageBean {
	String csCode;
	String csName;
	String opCode;
	String opName;
	String teId;
	String teName;
	String stuId;
	String stuName;
	Date attDate;
	String status;
	String ctCode;
	String ctTitle;
	String ctComments;
	
	Date startDate;
	Date endDate;
	
	String mbType;
}
