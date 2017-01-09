

import guiPractice.GUIApplication;

@SuppressWarnings("serial")
public class SimonGameEduardo extends GUIApplication {
	
	public static SimonGameEduardo game;
	public static SimonScreenEduardo screen;

	public static void main(String[] args) {
		game = new SimonGameEduardo();
		Thread app = new Thread(game);
		app.start();
	}

	@Override
	protected void initScreen() {
		screen = new SimonScreenEduardo(getWidth(), getHeight());
		setScreen(screen);
	}

}
