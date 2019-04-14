package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
public class GUI extends JFrame implements ActionListener {
	public GUI() {
		initializePanel();
	}
	@SuppressWarnings("restriction")
	public void initializePanel() {
		JFrame all = new JFrame();
		Pane washingMachine = new StackPane();
//		Circle drum = new Circle();
//		drum.setRadius(20);
//		drum.setCenterX(washingMachine.getAlignmentX()+250);
//		drum.setCenterY(washingMachine.getAlignmentY()+250);
//		washingMachine.add(drum);
		JPanel buttons = new JPanel();
		JButton pause = new JButton("Pause");
		JButton resume = new JButton("Resume");
		pause.setActionCommand("Pause");
		pause.addActionListener(this);
		resume.setActionCommand("Resume");
		resume.addActionListener(this);
		buttons.add(pause);
		buttons.add(resume);
		all.add(buttons);
		all.setSize(200, 300);
		all.setVisible(true);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
