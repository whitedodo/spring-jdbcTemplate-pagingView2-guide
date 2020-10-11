package study.poly;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.website.example.common.RootConfig;
import com.website.example.test.ResultAOP_XmlConfig;
import com.website.example.test.ResultAOP_JavaConfig;

// 방법 1-Java, @EnableAspectJAutoProxy
public class TestMain {

	@Test
	public void TestTV() {
		/*
		BeanFactory bean = new BeanFactory();
		TV tv = (TV) bean.getBeans("hama");
		
		//System.out.println( "출력1" );
		//tv.powerOn();
		
		tv = (TV) bean.getBeans("roll");

		//System.out.println( "출력2" );
		//tv.powerOn();
		 
		*/
	}
	
	@Test
	public void aopXMLTest() {
		
		/*
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		//TV tv1 = (TV) factory.getBean("hama");
		//tv1.volumeDown();
		ResultAOP_XmlConfig rAOP = (ResultAOP_XmlConfig) factory.getBean("resultAOP");
		
		rAOP.method1();
		factory.close();
		*/
		
		
		// 방식2
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ResultAOP_XmlConfig rAOP = ctx.getBean("resultAOP", ResultAOP_XmlConfig.class);
		rAOP.method1();
		
		ctx.close();
		
		
		
	}
	
	@Test
	public void aopJavaTest() {

		// 방법 1
		/*
		// Java 방식 - AOP
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(RootConfig.class);

		// 1. Aspect 적용
		// 소문자로 입력(Calculator -> calculator로)
		ResultAOP_JavaConfig cal = context.getBean(ResultAOP_JavaConfig.class);
		cal.method1();
		*/
		
		/*
		// 방법 2
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationJavaAOP.xml");
		
		ResultAOP_JavaConfig cal = factory.getBean(ResultAOP_JavaConfig.class);
		cal.method1();
		*/
	}
	
}
