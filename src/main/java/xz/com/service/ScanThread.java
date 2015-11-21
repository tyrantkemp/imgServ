package xz.com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import xz.com.bean.Documents;
import xz.com.bean.TopAndDown;
import xz.com.controllers.HomeCtl;
import xz.com.dao.ImgInfoDao;
import xz.com.dao.RegisterDao;
import xz.com.utils.Utils;



public class ScanThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ScanThread.class);
	private ImgInfoDao imgdao;
	
	public static List<Documents> list= new ArrayList<Documents>();
	public static Map<String,Integer> topMap = new LinkedHashMap();
	public static Map<String,Integer> downMap = new LinkedHashMap();
	
	
	public Map<String,Integer> sortedByTop(List<Documents> list){
		@SuppressWarnings("unchecked")
		Map<String,Integer> oldmap = new LinkedHashMap();
		for (Documents bean : list) {
			oldmap.put(bean.getImgId(), bean.getTop());	
		}
		return Utils.sortMap(oldmap);
		
	}
	
	public Map<String,Integer> sortedByDown(List<Documents> list){
		Map<String,Integer> oldmap = new LinkedHashMap();
		for (Documents bean : list) {

			
			oldmap.put(bean.getImgId(), bean.getDown());	
		}
		System.out.println("oldmap:"+oldmap
				.size());
		
		
		return Utils.sortMap(oldmap);
	}
	
	
	
	
    public void run() {    
        while (true) {    
            try {    
            	
            	System.out.println("开始thread循环");
            	imgdao=new ImgInfoDao();
            	list = imgdao.getAllImgInfoList();
            	
            	topMap = sortedByTop(list);
            	Utils.printMap(topMap);          	
            	
            	
            	downMap = sortedByDown(list);
            	Utils.printMap(downMap);          	

            	
                if (null==topMap||null==downMap) {    
                  
                  logger.debug("获取排序失败 10秒钟后再次获取");
                    // 休眠10秒    
                  Thread.sleep(10 * 1000);  
                   
                } else {    
                    logger.debug("获取排序成功 60秒钟后再次获取");

                    // 获取，60秒后再获取    
                    Thread.sleep(60 * 1000);    
                }    
            } catch (InterruptedException e) {    
                try {    
                    Thread.sleep(60 * 1000);    
                } catch (InterruptedException e1) {    
                   e1.printStackTrace();
                }    
               e.printStackTrace();    
            }    
        }    
    }   
}
