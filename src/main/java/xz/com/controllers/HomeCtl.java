package xz.com.controllers;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xz.com.bean.Documents;
import xz.com.dao.ImgInfoDao;
import xz.com.dao.RegisterDao;
import xz.com.service.AppServiceImpl;
import xz.com.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller

@RequestMapping(value="/app")
public class HomeCtl {
	@Autowired
	private RegisterDao dao;
	
	@Autowired 
	private ImgInfoDao imgdao;
	
	@Autowired 
	private AppServiceImpl appser;
	
    private static Logger logger = LoggerFactory.getLogger(HomeCtl.class);

    @RequestMapping(value={"/index"}, method = {RequestMethod.GET})
    public ModelAndView index(){
    	appser.sayHello();
    	System.out.println("哈哈");
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.addObject("message", "Hello World!");  
        modelAndView.setViewName("index");  
        return modelAndView;
    }
    
	@RequestMapping(value="/register",method={RequestMethod.POST})
	public void register(HttpServletRequest request,HttpServletResponse response){
		String username= request.getParameter("username");
    	String mail= request.getParameter("mail");
    	String password= request.getParameter("password");
    	//返回注册成功与否信息
    	Utils.writeBackInfo(appser.register(username, password, mail),response);		
	}
	
	//登录验证
	@RequestMapping(value ="/verify",method={RequestMethod.POST})
	public void verify(HttpServletRequest request,HttpServletResponse response){
		String username= request.getParameter("username");
    	String password= request.getParameter("password");
    	Utils.writeBackInfo(appser.verifyUser(username, password), response);
	}
	
	//上传单个图片
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST) 
    public void handleFileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response) throws FileUploadException{
    	String fileDir = request.getRealPath("/")+"/WEB-INF/pic";
    	Utils.writeBackInfo(appser.upLoadFile(file, request.getParameter("name"),fileDir), response);
    }
	
    
    //获取所有图片及图片评论信息 图片信息都以json　string 格式传输
	@RequestMapping(value="/getPicInfo",method={RequestMethod.GET})
	public void getPicInfo(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException{
		String type = request.getParameter("type");
		logger.debug("type:"+type);
		List<String> namelist =  Utils.GetPath(request.getRealPath("/")+"/WEB-INF/pic");
		logger.debug(String.valueOf(namelist.size()));
		List<Documents> doclist = new ArrayList<Documents>();
		for (String picname : namelist) {
			Documents doc= imgdao.getImgInfoByImgID(Utils.MD5(picname));
			if(null==doc){
				doc = new Documents(Utils.MD5(picname),"http://tyrantkemp.imwork.net:21848/test1/pic/"+picname);
				System.out.println("doc:"+doc.toString());
				imgdao.addImgInfo(doc);
			}
			doclist.add(doc);
		}
        String json = JSON.toJSONString(doclist);
        logger.info("jsonstr:"+json);
		Utils.writeBackInfo(json,response);

		
	}
}
