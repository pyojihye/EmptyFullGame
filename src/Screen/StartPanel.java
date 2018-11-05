package Screen;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Method.*;


class ArriveBarLabel extends JLabel{
	public int barSize=0;
	public int maxBarSize;
	public int count=0;
	public ArriveBarLabel(int maxBarSize) {
		this.maxBarSize = maxBarSize;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(123,195,169));
		int width=(int) (((double)(getWidth()))/maxBarSize*barSize);
		if(width==0) return;
		g.fillRect(0, 0, width, this.getHeight());
	}
	void fill(){
		if(barSize==maxBarSize){
			consume();
			count++;
		}
		barSize=barSize+2;
		repaint();
	}
	void consume(){
		if(barSize==0){
			try{
				wait();
			}catch(InterruptedException e){ return;}
		}
		barSize=0;
		repaint();
	}
	void stop(){
		return;
	}
}
class ArriveThread extends Thread{
	ArriveBarLabel bar;

	ArriveThread(ArriveBarLabel bar){
		this.bar=bar;
	}
	public void run(){
		while(true){
			try{
				sleep(50);
				bar.fill();
			}catch(InterruptedException e) {return;}
		}
	}
}


public class StartPanel extends JPanel implements Runnable{
	JLabel StartLabel;
	JLabel StoryLabel;
	JLabel SpringLabel;
	JLabel SummerLabel;
	JLabel FallLabel;
	JLabel WinterLabel;
	JLabel GameClearLabel;
	JLabel GameOverLabel;
	int count=0;
	
	public ArriveBarLabel bar = new ArriveBarLabel(450);
	Thread th;
	ArriveThread arriveTh = new ArriveThread(bar);
	public StartPanel(){
		setLayout(null);

		ImageIcon StartScreen = new ImageIcon("images/screen/StartImage.jpg");
		ImageIcon StoryScreen = new ImageIcon("images/screen/StoryImage.jpg");
		ImageIcon SpringScreen = new ImageIcon("images/screen/spring.jpg");
		ImageIcon SummerScreen = new ImageIcon("images/screen/summer.jpg");
		ImageIcon FallScreen = new ImageIcon("images/screen/fall.jpg");
		ImageIcon WinterScreen = new ImageIcon("images/screen/winter.jpg");
		ImageIcon GameClear = new ImageIcon("images/screen/GameClear.jpg");

		StartLabel = new JLabel(StartScreen);
		StoryLabel = new JLabel(StoryScreen);
		SpringLabel = new JLabel(SpringScreen);
		SummerLabel = new JLabel(SummerScreen);
		FallLabel = new JLabel(FallScreen);
		WinterLabel = new JLabel(WinterScreen);
		GameClearLabel = new JLabel(GameClear);

		StartLabel.setSize(StartScreen.getIconWidth(),StartScreen.getIconHeight());
		StoryLabel.setSize(StoryScreen.getIconWidth(),StoryScreen.getIconHeight());	
		SpringLabel.setSize(SpringScreen.getIconWidth(),SpringScreen.getIconHeight());	
		SummerLabel.setSize(SummerScreen.getIconWidth(), SummerScreen.getIconHeight());
		FallLabel.setSize(FallScreen.getIconWidth(),FallScreen.getIconHeight());	
		WinterLabel.setSize(WinterScreen.getIconWidth(),WinterScreen.getIconHeight());
		GameClearLabel.setSize(GameClear.getIconWidth(),GameClear.getIconHeight());	
		

		SpringLabel.setLocation(0,30);
		SummerLabel.setLocation(0,30);
		FallLabel.setLocation(0,30);
		WinterLabel.setLocation(0,30);
		
		
		ImageIcon gameover = new ImageIcon("images/screen/GameOver.jpg");
		GameOverLabel = new JLabel(gameover);
		GameOverLabel.setSize(gameover.getIconWidth(),gameover.getIconHeight());
		add(GameClearLabel);
		add(GameOverLabel);
		GameOverLabel.setVisible(false);
		
		Game game = new Game(this,GameOverLabel);
		add(game);
		add(StartLabel);
		
		
		
		GameClearLabel.setVisible(false);
		JButton StartButton = new JButton("START");
		StartButton.setSize(150,80);
		StartButton.setLocation(230,350);
		StartButton.setFont(new Font("Gothic", Font.BOLD, 30));
		th=new Thread(this);
		add(StartButton);

		StartButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){ 
				StartButton.setVisible(false);
				StartLabel.setVisible(false);
				remove(StartButton);
				remove(StartLabel);
				JButton StoryButton = new JButton("Ω√¿€");
				StoryButton.setSize(80, 50);
				StoryButton.setLocation(320, 650);
				StoryButton.setFont(new Font("Gothic", Font.BOLD, 15));
				add(StoryButton);
				add(StoryLabel);

				StoryButton.addMouseListener(new MouseAdapter(){
					public void mouseReleased(MouseEvent e){
						StoryButton.setVisible(false);
						StoryLabel.setVisible(false);
						remove(StoryButton);
						remove(StoryLabel);
						
						th.start();
						bar.setBackground(new Color(245,183,146));
						bar.setOpaque(true);
						bar.setLocation(0,0);
						bar.setSize(450,30);
						add(bar);
						
						arriveTh.start();
						game.StartGame();
					}
				});
			}
		});
	}
	public void run() {
		int labelcount=0;
		while(true){
			if(GameOverLabel.isVisible()){
				arriveTh.interrupt();
				remove(bar);
				bar.setVisible(false);
				break;
				
			}
			try{
				if(bar.count==0&&labelcount==0){
					add(SpringLabel);
					labelcount++;
				}
				else if(bar.count==1&&labelcount==1){
					add(SummerLabel);
					SpringLabel.setVisible(false);
					labelcount++;
					
				}
				else if(bar.count==2&&labelcount==2){
					SummerLabel.setVisible(false);
					add(FallLabel);
					labelcount++;
				}
				else if(bar.count==3&&labelcount==3){
					FallLabel.setVisible(false);
					add(WinterLabel);
					labelcount++;
				}
				else if(bar.count==4&&labelcount==4){
					WinterLabel.setVisible(false);
					bar.setVisible(false);
					
					remove(SpringLabel);
					remove(SummerLabel);
					remove(FallLabel);
					remove(WinterLabel);
					GameClearLabel.setVisible(true);
					break;
				}
				Thread.sleep(0);
			}catch(InterruptedException e) {}
		}
	}
	
}
