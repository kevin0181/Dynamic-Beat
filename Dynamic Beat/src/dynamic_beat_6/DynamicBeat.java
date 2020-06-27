package dynamic_beat_6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //자바에서 GUI 기반의 프로그램을 만들기위해 가장 기본적으로 상속받아야하는 부분.

public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스
	private Graphics screenGraphic; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스

	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/Vexento_startImg.png")).getImage();

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage(); // 이미지를
																															// 담을
																															// 수
																															// 있는
																															// 객체
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/Vexento_Title.png")).getImage();
	
	// main 클래스의 위치를 기반으로 image 파일을 얻어온 후 이미지 인스턴스를 넣고 초기화함
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")); // 시작
																														// 버튼
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));

	private ImageIcon rightButtonImage = new ImageIcon(Main.class.getResource("../images/rightButton.png")); // 오른쪽 곡 넘김
																												// 버튼
	private ImageIcon leftButtonImage = new ImageIcon(Main.class.getResource("../images/leftButton.png")); // 왼쪽 곡 넘김 버튼

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 전체 게임창의 크기를 정해줌
		setResizable(false); // 사용자가 게임창의 크기를 인위적으로 줄이거나 키울 수 없음
		setLocationRelativeTo(null); // 게임을 실행 했을때 게임창이 정 가운데에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료시 프로그램 전체가 종료.
		setVisible(true); // 게임창이 정상적으로 출력해주는 부분 (기본값 : false)
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		exitButton.setBounds(1220, 45, 50, 50);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서가 클릭커서로
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서가 일반 커서로

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
				// 게임 시작 이벤트
				startButton.setVisible(false); // startButton과 quitButton을 보이지 않게 함
				quitButton.setVisible(false);
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();

				isMainScreen = true;

			}
		});
		add(startButton);

		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("wayButton_clickSouund.mp3", false);
				buttonmusic.start();
				// 왼쪽 버튼 이벤트
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("wayButton_clickSouund.mp3", false);
				buttonmusic.start();
				// 오른쪽 버튼 이벤트
			}
		});
		add(rightButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // 마우스 이벤트
				mouseX = e.getX(); // 이벤트 발생시 x,y좌표 얻어옴
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) { // 드래그 이벤트
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // 드래그 할때 순간순간의 좌표를 얻어와 게임창의 위치를 변경.
			}
		});
		add(menuBar);

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
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}

		paintComponents(g); // JLabel 같은것들을 JFrame 안에 추가하는것. 항상 존재한는 이미지 이므로 paintComponents를 사용.
		this.repaint(); // 가장 첫번째로 화면에 그려주는 함수.
	}

}
