package xz.com.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import xz.com.bean.UserInfo;
import xz.com.controllers.HomeCtl;
import xz.com.dao.RegisterDao;
import xz.com.utils.Utils;


@Service
public class AppServiceImpl implements AppService {
    private static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

	@Autowired 
	private RegisterDao dao;
	public void sayHello(){
		System.out.println("hello");
	}
	
	public String register(String username, String password,String mail){
		//检查用户是否重复
        if(dao.isUserRepeat(username)){
        	
        	return "fasle";
        }
    	UserInfo userinfo=new UserInfo();
    	userinfo.setMail(mail);
    	userinfo.setUsername(username);
    	userinfo.setPassword(password);
    	logger.debug("info:"+username+":"+mail+":"+password);
    	//RegisterDao dao=new RegisterDao();
    	dao.insertUserInfo(userinfo);
    	return "true";	
	}

	//校验用户名密码
	public String verifyUser(String username, String password){
    	if(dao.verifyUserInfo(username,password)){
        	return "true";
    	}else {
    		return "false";
    	}
	}
	//上传单张图片
	public String upLoadFile(CommonsMultipartFile file,String filename,String fileDir){
		long startTime=System.currentTimeMillis();   //获取开始时间  
        if(!file.isEmpty()){  
            try {  
            	System.out.println("path:"+fileDir);
                File ouputimage = new File(fileDir+"/"+filename); //新建一个文件
                logger.debug(ouputimage.getPath());
                try {
                file.getFileItem().write(ouputimage); //将上传的文件写入新建的文件中
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }catch(Exception e){
           	 e.printStackTrace();
           	 logger.debug("上传文件"+filename+"失败");
           	 return "false";
            }

        }
        long endTime=System.currentTimeMillis(); //获取结束时间  
      	logger.debug("上传文件"+filename+"成功，耗时:"+(endTime-startTime));

        return "success"; 
	}


}
