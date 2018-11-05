package main;

import javax.swing.*;
import Screen.*;
import Method.*;

public class EmptyFullGameFrame extends JFrame{
	public boolean left=false, right=false;
	public EmptyFullGameFrame(){
		setTitle("≈÷¿Â? ≈Î¿Â!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,750);
		setResizable(false);
		StartPanel startpanel=new StartPanel();
		setContentPane(startpanel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new EmptyFullGameFrame();
		new Music();
	}
}
