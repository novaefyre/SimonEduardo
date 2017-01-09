

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceEduardo extends Clickable {

	void setColor(Color color);

	void setX(int i);

	void setY(int i);
	
	public void setAction(Action a);

	void highlight();

	void dim();

}
