package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeBean {
	String mbId;
	String csCode;
	String csName;
	String opCode;
	String opName;
	String ntCode;
	String ntType;
	String ntContents;
	Date createTime;
}
