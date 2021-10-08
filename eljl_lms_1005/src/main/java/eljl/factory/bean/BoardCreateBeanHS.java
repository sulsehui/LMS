package eljl.factory.bean;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardCreateBeanHS {
	String mbId;
	String mbName;
	String csCode;
	String csName;
	String opCode;
	String opName;
	String ssCode; //추가
	String ssName; //추가
	String numCode;
	String startDate;
	String endDate;
	String title;
	String contents;
	String answer;
    String stickerPath;
	MultipartFile mbFile;
	Date createTime;
}
