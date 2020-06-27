package dynamic_beat_9;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI()); // 파일을 가져오는 부분
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() { // 실행되고 있는 음악의 시간의 위치를 알려줌.
		if (player == null) {
			return 0;
		}
		return player.getPosition();
	}

	public void close() { // 곡이 끝났을때.
		isLoop = false;
		player.close();
		this.interrupt(); // 쓰레드를 중지상태로 만듬.
	}

	@Override
	public void run() { // 쓰레드 사용시 필수.
		try {

			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
