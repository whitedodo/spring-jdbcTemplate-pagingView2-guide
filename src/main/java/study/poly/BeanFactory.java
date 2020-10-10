package study.poly;

public class BeanFactory {

	public Object getBeans(String beannames) {
		
		TV tv = null;
		
		if ( beannames.equals("hama")) {
			
			tv = new HamaTv();
			
		}else if (beannames.equals("roll")) {
			
			tv = new RollTV();
			
		}
		
		return tv;
	}
	
}
