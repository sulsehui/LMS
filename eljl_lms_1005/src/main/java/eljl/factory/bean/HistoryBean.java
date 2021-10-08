package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class HistoryBean {
	String mbId;
	String mbPw;
	String mbType;
	String status;
	String publicIp;
	String privateIp;
	int method;
	Date hDate;

}
