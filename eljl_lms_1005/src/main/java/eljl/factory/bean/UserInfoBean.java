package eljl.factory.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserInfoBean {
	String mbId;
	String mbName;
	String mbPw;
	String mbMail;
	String mbPhone;
	String status;
	String mbType;
	String mbAuth;
	
    String stickerPath;
	MultipartFile mbFile;
	String fileName;
	
}
