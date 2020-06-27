package dynamic_beat_15;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // 노트가 생성후 1초뒤에 판정라인에 도착하는 공식
	private String noteType;
	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void Close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;
		if (y > 620) {
			System.out.println("Miss");
			Close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME); // 1초에 100번 실행
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}

	public void judge() {
		if (y >= 613) {
			System.out.println("Late");
			Close();
		} else if (y >= 600) {
			System.out.println("Good");
			Close();
		} else if (y >= 587) {
			System.out.println("Great");
			Close();
		} else if (y >= 573) {
			System.out.println("Perfect");
			Close();
		} else if (y >= 565) {
			System.out.println("Great");
			Close();
		} else if (y >= 550) {
			System.out.println("Good");
			Close();
		} else if (y >= 535) {
			System.out.println("Early");
			Close();
		}
	}

}
