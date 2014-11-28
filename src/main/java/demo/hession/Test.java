package demo.hession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pingan.wlt.dc.umic.rmi.BusinessException;
import com.pingan.wlt.dc.umic.rmi.UmRmiServiceFactory;
@Component
public class Test {

	public static void main(String[] args) throws BusinessException {
		Map<String, Object> map=new HashMap<String, Object>();
		Object object=UmRmiServiceFactory.getSecurityService().getExtResourcesByTypeAndUmid("UM","e10adc3949ba59abbe56e057f20f883e","MENU","system");
		System.out.println();
	}

}
