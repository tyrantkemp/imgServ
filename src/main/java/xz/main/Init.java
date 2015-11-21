package xz.main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import xz.com.service.ScanThread;

public class Init extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		System.out.println("开始扫描线程");
		// 启动定时获取access_token的线程
		new Thread(new ScanThread()).start();

	}

}
