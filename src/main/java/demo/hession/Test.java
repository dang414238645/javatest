package demo.hession;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pingan.wlt.dc.umic.rmi.BusinessException;
import com.pingan.wlt.dc.umic.rmi.UmRmiServiceFactory;
@Component
public class Test {

	public static void main(String[] args) throws BusinessException {
		List<com.pingan.jrkj.datacenter.dcds.vo.security.User> list=UmRmiServiceFactory.getSecurityService()
				.getUsersByRolename("PARTNER_SETTLE_STAFF");
		
		UmRmiServiceFactory.getSecurityService().getAuthMenu("Finance", "MENU", "test", "e10adc3949ba59abbe56e057f20f883e");
		System.out.println();
	}

}
