package xz.com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired AppService ser;
	


    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            startScanThread();
        }
    }

    private void startScanThread() {
    	ser.sayHello();
		System.out.println("��ʼɨ���߳�");
		// ������ʱ��ȡaccess_token���߳�
		new Thread(new ScanThread()).start();
    }
    
}