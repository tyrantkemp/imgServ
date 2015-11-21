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
		//����û��Ƿ��ظ�
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

	//У���û�������
	public String verifyUser(String username, String password){
    	if(dao.verifyUserInfo(username,password)){
        	return "true";
    	}else {
    		return "false";
    	}
	}
	//�ϴ�����ͼƬ
	public String upLoadFile(CommonsMultipartFile file,String filename,String fileDir){
		long startTime=System.currentTimeMillis();   //��ȡ��ʼʱ��  
        if(!file.isEmpty()){  
            try {  
            	System.out.println("path:"+fileDir);
                File ouputimage = new File(fileDir+"/"+filename); //�½�һ���ļ�
                logger.debug(ouputimage.getPath());
                try {
                file.getFileItem().write(ouputimage); //���ϴ����ļ�д���½����ļ���
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }catch(Exception e){
           	 e.printStackTrace();
           	 logger.debug("�ϴ��ļ�"+filename+"ʧ��");
           	 return "false";
            }

        }
        long endTime=System.currentTimeMillis(); //��ȡ����ʱ��  
      	logger.debug("�ϴ��ļ�"+filename+"�ɹ�����ʱ:"+(endTime-startTime));

        return "success"; 
	}


}
