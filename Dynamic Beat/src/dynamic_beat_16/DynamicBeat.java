package dynamic_beat_16;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //자바에서 GUI 기반의 프로그램을 만들기위해 가장 기본적으로 상속받아야하는 부분.

public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스
	private Graphics screenGraphic; // 더블 퍼버링을 위해서 전체화면에 대한 이미지를 담는 인스턴스

	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage(); // 이미지를
																															// 담을
																															// 수
																															// 있는

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

	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));

	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png")); // 뒤로가기버튼

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image selectedImage; // 노래 선택할 때 이미지 // 객체
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0;

	Music introMusic = new Music("Markvard - Cause Of You(intro).mp3", true); // 시작화면에서 음악이 무한적으로 재생되도록

	public static Game game;

	public DynamicBeat() {

		trackList.add(new Track("Vexento_Title.png", "Vexento_startImg.png", "Vexento_gameImg.jpg",
				"We Are One - Vexento [Vlog No Copyright Music].mp3",
				"We Are One - Vexento [Vlog No Copyright Music].mp3", "We Are One - Vexento"));
		trackList.add(new Track("Epiphany_Title.png", "Epiphany_startimg.png", "Epiphany_gameImg.jpg",
				"MusicbyAden - Epiphany (Vlog No Copyright Music).mp3",
				"MusicbyAden - Epiphany (Vlog No Copyright Music).mp3", "Epiphany - MusicbyAden"));
		trackList.add(new Track("A Himitsu_Title.png", "A Himitsu_startImg.jpg", "A Himitsu_gameImg.jpg",
				"Adventures - A Himitsu (No Copyright Music).mp3", "Adventures - A Himitsu (No Copyright Music).mp3",
				"Adventures - A Himitsu"));

		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 전체 게임창의 크기를 정해줌
		setResizable(false); // 사용자가 게임창의 크기를 인위적으로 줄이거나 키울 수 없음
		setLocationRelativeTo(null); // 게임을 실행 했을때 게임창이 정 가운데에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 종료시 프로그램 전체가 종료.
		setVisible(true); // 게임창이 정상적으로 출력해주는 부분 (기본값 : false)
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new dynamic_beat_16.KeyListener());

		introMusic.start();

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
				introMusic.close();
				enterMain();
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
				selectLeft();
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
				selectRight();
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("wayButton_clickSouund.mp3", false);
				buttonmusic.start();
				// 난이도 쉬움 이벤트
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("wayButton_clickSouund.mp3", false);
				buttonmusic.start();
				// 난이도 어려움 이벤트
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(3, 30, 250, 67);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonmusic = new Music("buttonMusic.mp3", false);
				buttonmusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonmusic = new Music("wayButton_clickSouund.mp3", false);
				buttonmusic.start();
				// 메인화면으로 돌아가는 이벤트
				backMain();
			}
		});
		add(backButton);

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

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280*720 만큼의 이미지를 만들고 screenimage에 넣음
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);

		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}

		if (isGameScreen) {
			game.screenDraw(g);
		}

		paintComponents(g); // JLabel 같은것들을 JFrame 안에 추가하는것. 항상 존재한는 이미지 이므로 paintComponents를 사용.
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.repaint(); // 가장 첫번째로 화면에 그려주는 함수.
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);

		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();

		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());

		game.start(); // run 함수 실행.
		setFocusable(true);
		requestFocus();
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		backButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}

	public void enterMain() {
		selectTrack(0);
		// 게임 시작 이벤트
		startButton.setVisible(false); // startButton과 quitButton을 보이지 않게 함
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;

		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);

		introMusic.close();

	}

}
