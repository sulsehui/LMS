package eljl.factory.bean;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardCreateBean {
	String mbId;
	String mbName;
	String csCode;
	String csName;
	String opCode;
	String opName;
	String numCode;
	Date startDate;
	Date endDate;
	String title;
	String contents;
	String answer;
	List<MultipartFile> file;
	Date createTime;
}
