package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame; //자바에서 GUI 기반의 프로그램을 만들기위해 가장 기본적으로 상속받아야하는 부분.

public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스
	private Graphics screenGraphic; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스

	private Image introBackground; // 이미지를 담을 수 있는 객체

	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 전체 게임창의 크기를 정해줌
		setResizable(false); // 사용자가 게임창의 크기를 인위적으로 줄이거나 키울 수 없음
		setLocationRelativeTo(null); // 게임을 실행 했을때 게임창이 정 가운데에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료시 프로그램 전체가 종료.
		setVisible(true); // 게임창이 정상적으로 출력해주는 부분 (기본값 : false)

		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
		// main 클래스의 위치를 기반으로 image 파일을 얻어온 후 이미지 인스턴스를 넣고 초기화함

		Music introMusic = new Music("Markvard - Cause Of You(intro).mp3", true); // 시작화면에서 음악이 무한적으로 재생되도록
		introMusic.start();
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720 만큼의 이미지를 만들고 screenimage에 넣음
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint(); // 가장 첫번째로 화면에 그려주는 함수.
	}

}
