package eljl.factory.bean;

import java.util.Date;

import lombok.Data;

@Data
public class HistroyBean {
	String mbId;
	String publicIp;
	String privateIp;
	String method;
	Date hDate;
}
