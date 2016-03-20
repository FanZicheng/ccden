package cn.coaledu;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coaledu.basic.dto.OrgTypeDto;
import com.coaledu.basic.dto.TreeDto;
import com.coaledu.org.iservice.IOrgService;
import com.coaledu.org.iservice.IOrgTypeService;
import com.coaledu.org.iservice.IPositionService;
import com.coaledu.org.model.Org;
import com.coaledu.org.model.Position;

public class testCase002 {

	private ApplicationContext ac;
	
	@Test
	public void test001() {
		ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		IOrgService iOrgService = (IOrgService)ac.getBean("orgService");
		List<TreeDto> list = iOrgService.getOrgTree();
		System.out.println(list);
	}
	
	@Test
	public void test002() {
		ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
		IPositionService iPositionService = (IPositionService)ac.getBean("positionService");
		List<Position> list = iPositionService.getAllPosition();
		System.out.println(list.isEmpty());
	}
}
