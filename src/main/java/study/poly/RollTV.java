package study.poly;

public class RollTV implements TV{

	@Override
	public void powerOn() {
		System.out.println("SamsungTV - Power On");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV - Power Off");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV - Volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV- Volume Down");		
	}
	
}
