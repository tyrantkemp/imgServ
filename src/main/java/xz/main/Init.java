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

		System.out.println("��ʼɨ���߳�");
		// ������ʱ��ȡaccess_token���߳�
		new Thread(new ScanThread()).start();

	}

}
