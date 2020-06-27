package dynamic_beat_15;

public class Main {

	public static final int SCREEN_WIDTH = 1280; // final 한번 선언 되면 절대 바뀌지 않음.
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2; //노트가 생성후 판정바에 도달하기 까지의 시간
	
	// 더블 버퍼링 사용 - 현재 프로그램의 전체화면의 크기에 맞는 이미지를 매 순간마다 생성하여 원하는 컴포넌트에 출력함.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DynamicBeat();
	}

}
