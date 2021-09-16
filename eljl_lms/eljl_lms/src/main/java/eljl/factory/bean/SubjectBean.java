package eljl.factory.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class SubjectBean {
	String mbId;
	String mbType;
	String csCode;
	String csName;
	String opCode;
	String opName;
	Date startDate;
	Date endDate;
	List<GradeBean> gbList;
	
}
