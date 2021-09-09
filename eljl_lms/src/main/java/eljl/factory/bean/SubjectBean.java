package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class SubjectBean {
	String mbId;
	String csCode;
	String csName;
	String opCode;
	String opName;
	Date startDate;
	Date endDate;
}
