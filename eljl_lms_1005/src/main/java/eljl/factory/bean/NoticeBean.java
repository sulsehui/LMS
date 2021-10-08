package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeBean {
	String mbId;
	String teId;
	String csCode;
	String csName;
	String opCode;
	String opName;
	String ntCode;
	String ntType;
	String ntTitle;
	String ntContents;
	Date createTime;
}
