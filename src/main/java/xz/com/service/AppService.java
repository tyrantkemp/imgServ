package xz.com.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface AppService {

	public void sayHello();
	
	public String register(String username, String password,String mail);
	
	public String verifyUser(String username, String password);
	
	public String upLoadFile(CommonsMultipartFile file,String filename,String fileDir);
}
