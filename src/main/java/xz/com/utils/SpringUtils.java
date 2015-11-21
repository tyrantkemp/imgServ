package xz.com.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class SpringUtils {

    private static ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    
    public static Object getBean(String beanName){
         return ctx.getBean(beanName);
    }    
}