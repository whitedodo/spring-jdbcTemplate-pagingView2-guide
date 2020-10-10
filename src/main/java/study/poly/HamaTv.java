package study.poly;

public class HamaTv implements TV{

	@Override
	public void powerOn() {
		System.out.println("LGTV - Power On");
	}

	@Override
	public void powerOff() {
		System.out.println("LGTV - Power Off");
	}

	@Override
	public void volumeUp() {
		System.out.println("LGTV - Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("LGTV - Volume Down");		
	}
	
}
