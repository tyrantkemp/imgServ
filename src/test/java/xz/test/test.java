package xz.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import xz.com.bean.Documents;
import xz.com.utils.Utils;  
  
  

public class test {  
    public static void main(String[] args){  
     //   String jsonStr = "{\"JACKIE_ZHANG\":\"张学友\",\"ANDY_LAU\":\"刘德华\",\"LIMING\":\"黎明\",\"Aaron_Kwok\":\"郭富城\"}" ;  
  
    	String jsonStr=Utils.ReadFile("C:/Users/tyrantxz/workspace/imgServ/src/tu_2.json");
    	System.out.println("jsonStr:"+jsonStr);
  
    	Documents doc = JSON.parseObject(jsonStr, Documents.class);
    	System.out.println(doc.toString());
    	
           JSONObject jsonObject = JSONObject.parseObject(jsonStr) ;  
           for(java.util.Map.Entry<String,Object> entry:jsonObject.entrySet()){  
               System.out.print(entry.getKey()+":"+entry.getValue()+"\t");  
           }  
        
    }  
}  