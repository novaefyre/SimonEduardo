

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.components.Action;
import guiPractice.ClickableScreen;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class SimonScreenEduardo extends ClickableScreen implements Runnable {

	private TextLabel label;
	private ButtonInterfaceEduardo[] buttons;
	private ProgressInterfaceEduardo progressBox;
	private ArrayList<MoveInterfaceEduardo> sequence;
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	
	public SimonScreenEduardo(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}
	
	public void changeText(String text){
		label.setText(text);
	}

	@Override
	public void run() {
		label.setText("");
	    nextRound();
	}

	public void nextRound() {
		acceptingInput = false;
		roundNumber++;
		sequence.add(randomMove());
		progressBox.setRound(roundNumber);
		progressBox.setSequenceSize(sequence.size());
		changeText("Simon's Turn");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		changeText("");
		playSequence();
		changeText("Your Turn");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	public void playSequence() {
		ButtonInterfaceEduardo b = null;
		for(int i = 0; i < sequence.size(); i++){
			if(b != null){
				b.dim();
			}
			b = sequence.get(sequenceIndex).getButton();
			b.highlight();
			long sleepTime;
			if(roundNumber != 1){
				sleepTime = 1000/(roundNumber-1)*7/8;
			}else{
				sleepTime = 10;
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		b.dim();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progressBox = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceEduardo>();
		//add 2 moves to start
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progressBox);
		viewObjects.add(label);
	}

	private MoveInterfaceEduardo randomMove() {
		ButtonInterfaceEduardo b;
		int i = (int) (Math.random()*buttons.length);
		while(i == lastSelectedButton){
			i = (int) (Math.random()*buttons.length);
		}
		b = buttons[i];
		lastSelectedButton = i;
		return getMove(b);
	}

	private MoveInterfaceEduardo getMove(ButtonInterfaceEduardo b) {
		// placeholder until Move is completed
		return null;
	}

	private ProgressInterfaceEduardo getProgress() {
		//Placeholder until partner completes progressBox
		return null;
	}
	
	
	private void addButtons() {
		int numberOfButtons = 4;
		Color[] colors = {Color.blue,Color.green,Color.red,Color.yellow,Color.orange};
		for(int i = 0; i < numberOfButtons; i++){
			final ButtonInterfaceEduardo b = getAButton();
			b.setColor(colors[i]);
			b.setX(60);
			b.setY(getWidth()/numberOfButtons*i);
			b.setAction(new Action(){
				public void act(){
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
									b.dim();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						blink.start();
						if(b == sequence.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}else{
							progressBox.gameOver();
						}
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenEduardo.this);
							nextRound.start(); 
						}
						addObject(b);
					}
				}
			});
		}
		
	}

	private ButtonInterfaceEduardo getAButton() {
		// placeholder for... well, you know the drill
		return null;
	}

}
