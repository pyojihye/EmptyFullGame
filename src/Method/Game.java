package Method;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import Screen.*;

public class Game extends JPanel{
	private StartPanel st;
	JLabel Money1Label;
	JLabel Money2Label;
	JLabel Money3Label;
	JLabel Money4Label;
	JLabel Money5Label;
	JLabel Money6Label;
	JLabel Money7Label;
	JLabel Money8Label;

	JLabel HumanLabel;
	JLabel BookLabel;
	JLabel BlouseLabel;
	JLabel CoffeeLabel;
	JLabel HamburgerLabel;
	JLabel HouseLabel;
	JLabel PantsLabel;
	JLabel GameOverLabel;
	Thread th;

	public Game(StartPanel startpanel,JLabel GameOverLabel){
		this.st=startpanel;
		this.GameOverLabel=GameOverLabel;


		ImageIcon money1 = new ImageIcon("images/screen/Money1.png");
		ImageIcon money2 = new ImageIcon("images/screen/Money2.png");
		ImageIcon money3 = new ImageIcon("images/screen/Money3.png");
		ImageIcon money4 = new ImageIcon("images/screen/Money4.png");
		ImageIcon money5 = new ImageIcon("images/screen/Money5.png");
		ImageIcon money6 = new ImageIcon("images/screen/Money6.png");
		ImageIcon money7 = new ImageIcon("images/screen/Money7.png");
		ImageIcon money8 = new ImageIcon("images/screen/Money8.png");
		ImageIcon human = new ImageIcon("images/icon/human.png");
		ImageIcon book = new ImageIcon("images/icon/book.png");
		ImageIcon blouse = new ImageIcon("images/icon/blouse.png");
		ImageIcon coffee = new ImageIcon("images/icon/coffee.png");
		ImageIcon house = new ImageIcon("images/icon/house.png");
		ImageIcon hamburger = new ImageIcon("images/icon/hamburger.png");
		ImageIcon pants = new ImageIcon("images/icon/pants.png");

		Money1Label = new JLabel(money1);
		Money2Label = new JLabel(money2);
		Money3Label = new JLabel(money3);
		Money4Label = new JLabel(money4);
		Money5Label = new JLabel(money5);
		Money6Label = new JLabel(money6);
		Money7Label = new JLabel(money7);
		Money8Label = new JLabel(money8);
		HumanLabel = new JLabel(human);
		BookLabel = new JLabel(book);
		BlouseLabel = new JLabel(blouse);
		CoffeeLabel = new JLabel(coffee);
		HouseLabel = new JLabel(house);
		HamburgerLabel = new JLabel(hamburger);
		PantsLabel = new JLabel(pants);

		Money1Label.setSize(money1.getIconWidth(),money1.getIconHeight());
		Money2Label.setSize(money2.getIconWidth(),money2.getIconHeight());
		Money3Label.setSize(money3.getIconWidth(),money3.getIconHeight());
		Money4Label.setSize(money4.getIconWidth(),money4.getIconHeight());
		Money5Label.setSize(money5.getIconWidth(),money5.getIconHeight());
		Money6Label.setSize(money6.getIconWidth(),money6.getIconHeight());
		Money7Label.setSize(money7.getIconWidth(),money7.getIconHeight());
		Money8Label.setSize(money8.getIconWidth(),money8.getIconHeight());
		HumanLabel.setSize(human.getIconWidth(),human.getIconHeight());
		BookLabel.setSize(book.getIconWidth(),book.getIconHeight());
		BlouseLabel.setSize(blouse.getIconWidth(),blouse.getIconHeight());
		CoffeeLabel.setSize(coffee.getIconWidth(),coffee.getIconHeight());
		HouseLabel.setSize(house.getIconWidth(),house.getIconHeight());
		HamburgerLabel.setSize(hamburger.getIconWidth(),hamburger.getIconHeight());
		PantsLabel.setSize(pants.getIconWidth(),pants.getIconHeight());

		Money1Label.setLocation(0,500);
		Money2Label.setLocation(0,500);
		Money3Label.setLocation(0,500);
		Money4Label.setLocation(0,500);
		Money5Label.setLocation(0,500);
		Money6Label.setLocation(0,500);
		Money7Label.setLocation(0,500);
		Money8Label.setLocation(0,500);
		HumanLabel.setLocation(200, 330);

		startpanel.add(HumanLabel);
		startpanel.add(BookLabel);
		startpanel.add(Money8Label);
		startpanel.add(Money7Label);
		startpanel.add(Money6Label);
		startpanel.add(Money5Label);
		startpanel.add(Money4Label);
		startpanel.add(Money3Label);
		startpanel.add(Money2Label);
		startpanel.add(Money1Label);
		startpanel.add(BlouseLabel);
		startpanel.add(CoffeeLabel);
		startpanel.add(HamburgerLabel);
		startpanel.add(HouseLabel);
		startpanel.add(PantsLabel);


		BookLabel.setVisible(false);
		HumanLabel.setVisible(false);
		Money1Label.setVisible(false);
		Money2Label.setVisible(false);
		Money3Label.setVisible(false);
		Money4Label.setVisible(false);
		Money5Label.setVisible(false);
		Money6Label.setVisible(false);
		Money7Label.setVisible(false);
		Money8Label.setVisible(false);
		BlouseLabel.setVisible(false);
		CoffeeLabel.setVisible(false);
		HamburgerLabel.setVisible(false);
		HouseLabel.setVisible(false);
		PantsLabel.setVisible(false);

	}
	public void StartGame(){
		HumanLabel.setVisible(true);
		Money1Label.setVisible(true);

		BookThread bookthread = new BookThread(BookLabel);
		bookthread.start();
		BlouseThread blouseThread = new BlouseThread(BlouseLabel);
		blouseThread.start();
		CoffeeThread coffeethread = new CoffeeThread(CoffeeLabel);
		coffeethread.start();
		HamburgerThread hamburgerthread = new HamburgerThread(HamburgerLabel);
		hamburgerthread.start();
		PantsThread pantsthread = new PantsThread(PantsLabel);
		pantsthread.start();
		HouseThread housethread = new HouseThread(HouseLabel);
		housethread.start();

		HumanThread HumanThread = new HumanThread(HumanLabel,BlouseLabel,BookLabel,CoffeeLabel,HamburgerLabel,HouseLabel,PantsLabel);
		HumanThread.start();

		Container c = getRootPane();
		c.requestFocus();
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){ 
				if (e.getKeyCode() == KeyEvent.VK_LEFT && HumanLabel.getX()>=0){
					int x=HumanLabel.getX()-3;
					HumanLabel.setLocation(x,HumanLabel.getY());
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT &&HumanLabel.getX()+64<=450) {
					int x=HumanLabel.getX()+3;
					HumanLabel.setLocation(x,HumanLabel.getY());
				}
			}
		});
	}
	class BookThread extends Thread{
		JLabel book;
		Random random = new Random();
		int bookX;
		public BookThread(JLabel book){
			this.book=book;
			bookX=random.nextInt(450)+1;
			book.setLocation(bookX,30);
		}
		public void run(){
			try {
				sleep(2000);
				BookLabel.setVisible(true);
			} catch (InterruptedException e1) {	}
			while(true){
				try{
					int bookY=book.getY()+1;
					book.setLocation(bookX,bookY);
					sleep(8);
					if(bookX+50>450)
						bookX=bookX-100;
					if(bookY+39==500){
						book.setVisible(false);
						bookX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						bookY=30;
						book.setLocation(bookX,bookY);
						book.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}

	class BlouseThread extends Thread{
		JLabel blouse;
		Random random = new Random();
		int blouseX;
		public BlouseThread(JLabel blouse){
			this.blouse=blouse;
			blouseX=random.nextInt(450)+1;
			blouse.setLocation(blouseX,30);
		}
		public void run(){
			try {
				sleep(40);
				BlouseLabel.setVisible(true);
			} catch (InterruptedException e1) {}

			while(true){
				try{
					int blouseY=blouse.getY()+1;
					blouse.setLocation(blouseX,blouseY);
					sleep(6);
					if(blouseX+50>450)
						blouseX=blouseX-100;
					if(blouseY+39==500){
						blouse.setVisible(false);
						blouseX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						blouseY=30;
						blouse.setLocation(blouseX,blouseY);
						blouse.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}
	class CoffeeThread extends Thread{
		JLabel coffee;
		Random random = new Random();
		int coffeeX;
		public CoffeeThread(JLabel coffee){
			this.coffee=coffee;
			coffeeX=random.nextInt(450)+1;
			coffee.setLocation(coffeeX,30);
		}
		public void run(){
			try {
				sleep(30);
				CoffeeLabel.setVisible(true);
			} catch (InterruptedException e1) {}

			while(true){
				try{
					int coffeeY=coffee.getY()+1;
					coffee.setLocation(coffeeX,coffeeY);
					sleep(7);
					if(coffeeX+29>450)
						coffeeX=coffeeX-58;
					if(coffeeY+39==500){
						coffee.setVisible(false);
						coffeeX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						coffeeY=30;
						coffee.setLocation(coffeeX,coffeeY);
						coffee.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}
	class HamburgerThread extends Thread{
		JLabel hamburger;
		Random random = new Random();
		int hamburgerX;
		public HamburgerThread(JLabel hamburger){
			this.hamburger=hamburger;
			hamburgerX=random.nextInt(450)+1;
			hamburger.setLocation(hamburgerX,30);
		}
		public void run(){
			try {
				sleep(10);
				HamburgerLabel.setVisible(true);
			} catch (InterruptedException e1) {}

			while(true){
				try{
					int hamburgerY=hamburger.getY()+1;
					hamburger.setLocation(hamburgerX,hamburgerY);
					sleep(5);
					if(hamburgerX+50>450){
						hamburgerX=hamburgerX-100;
					}
					if(hamburgerY+39==500){
						hamburger.setVisible(false);
						hamburgerX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						hamburgerY=30;
						hamburger.setLocation(hamburgerX,hamburgerY);
						hamburger.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}
	class HouseThread extends Thread{
		JLabel house;
		Random random = new Random();
		int houseX;
		public HouseThread(JLabel house){
			this.house=house;
			houseX=random.nextInt(450)+1;
			house.setLocation(houseX,30);
		}
		public void run(){
			try {
				sleep(450);
				HouseLabel.setVisible(true);
			} catch (InterruptedException e1) {}

			while(true){
				try{
					int houseY=house.getY()+1;
					house.setLocation(houseX,houseY);
					sleep(10);
					if(houseX+50>450)
						houseX=houseX-100;
					if(houseY+39==500){
						house.setVisible(false);
						houseX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						houseY=30;
						house.setLocation(houseX,houseY);
						house.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}
	class PantsThread extends Thread{
		JLabel pants;
		Random random = new Random();
		int pantsX;
		public PantsThread(JLabel pants){
			this.pants=pants;
			pantsX=random.nextInt(450)+1;
			pants.setLocation(pantsX,30);
		}
		public void run(){
			try {
				sleep(70);
				PantsLabel.setVisible(true);
			} catch (InterruptedException e1) {}

			while(true){
				try{
					int pantsY=pants.getY()+1;
					pants.setLocation(pantsX,pantsY);
					sleep(8);
					if(pantsX+28>450)
						pantsX=pantsX-56;
					if(pantsY+39==500){
						pants.setVisible(false);
						pantsX=random.nextInt(450)+1;
						sleep(random.nextInt(5)+1);
						pantsY=30;
						pants.setLocation(pantsX,pantsY);
						pants.setVisible(true);
					}
				}catch(InterruptedException e) {}
			}
		}
	}
	class HumanThread extends Thread{
		JLabel human;
		JLabel blouse;
		JLabel book;
		JLabel coffee;
		JLabel hamburger;
		JLabel house;
		JLabel pants;
		public int statuscount=0;
		int money = 300000;
		int index=300000/8;
		int spend;
		int current=money;
		Random moneyrandom = new Random();
		public HumanThread(JLabel humanLabel, JLabel blouseLabel, JLabel bookLabel, JLabel coffeeLabel, JLabel hamburgerLabel,
				JLabel houseLabel, JLabel pantsLabel) {
			this.human = humanLabel;
			this.blouse = blouseLabel;
			this.book = bookLabel;
			this.coffee = coffeeLabel;
			this.hamburger = hamburgerLabel;
			this.house =houseLabel;
			this.pants = pantsLabel;
		}

		public void run(){
			while(true){
				System.out.println("money : "+current);
				if(hit()){
					try {
						spend=moneyrandom.nextInt(30000)+1500;
						current=current-spend;
						spend=0;
						sleep(1000);
						if(current<money-(index*1)&&statuscount==0){
							Money2Label.setVisible(true);
							Money1Label.setVisible(false);
							statuscount++;
						}
						if(current<money-(index*2)&&statuscount==1){
							Money2Label.setVisible(false);
							Money3Label.setVisible(true); 
							statuscount++;
						}
						if(current<money-(index*3)&&statuscount==2){
							Money3Label.setVisible(false);
							Money4Label.setVisible(true);
							statuscount++;
						}
						if(current<money-(index*4)&&statuscount==3){
							Money4Label.setVisible(false);
							Money5Label.setVisible(true);
							statuscount++;
						}
						if(current<money-(index*5)&&statuscount==4){
							Money5Label.setVisible(false);
							Money6Label.setVisible(true);
							statuscount++;
						}
						if(current<money-(index*6)&&statuscount==5){
							Money6Label.setVisible(false);
							Money7Label.setVisible(true);
							statuscount++;
						}
						if(current<money-(index*7)&&statuscount==6){
							Money7Label.setVisible(false);
							Money8Label.setVisible(true);
							statuscount++;
						}
						if(current<0&&statuscount==7){
							Money8Label.setVisible(false);
							GameOverLabel.setVisible(true);
							statuscount++;
						}
					} catch (InterruptedException e) {}

				}

			}
		}
		private boolean hit() {
			if(blouse.isVisible()&&HumanContains(blouse.getX(), blouse.getY()) || HumanContains(blouse.getX() + blouse.getWidth() - 1, blouse.getY()) ||  HumanContains(blouse.getX() + blouse.getWidth() - 1, blouse.getY() + blouse.getHeight() - 1) || HumanContains(blouse.getX(), blouse.getY() + blouse.getHeight() - 1)){
				blouse.setVisible(false);
				blouse.setLocation(blouse.getX(),blouse.getY());
				return true;
			}
			else if(book.isVisible()&&HumanContains(book.getX(), book.getY()) || HumanContains(book.getX() + book.getWidth() - 1, book.getY()) ||  HumanContains(book.getX() + book.getWidth() - 1, book.getY() + book.getHeight() - 1) || HumanContains(book.getX(), book.getY() + book.getHeight() - 1)){
				book.setVisible(false);
				book.setLocation(book.getX(),book.getY());
				return true;
			}

			else if(coffee.isVisible()&&HumanContains(coffee.getX(), coffee.getY()) || HumanContains(coffee.getX() + coffee.getWidth() - 1, coffee.getY()) ||  HumanContains(coffee.getX() + coffee.getWidth() - 1, coffee.getY() + coffee.getHeight() - 1) || HumanContains(coffee.getX(), coffee.getY() + coffee.getHeight() - 1)){
				coffee.setVisible(false);
				coffee.setLocation(coffee.getX(),coffee.getY());
				return true;
			}

			else if(hamburger.isVisible()&&HumanContains(hamburger.getX(), hamburger.getY()) || HumanContains(hamburger.getX() + hamburger.getWidth() - 1, hamburger.getY()) ||  HumanContains(hamburger.getX() + hamburger.getWidth() - 1, hamburger.getY() + hamburger.getHeight() - 1) || HumanContains(hamburger.getX(), hamburger.getY() + hamburger.getHeight() - 1)){
				hamburger.setVisible(false);
				hamburger.setLocation(hamburger.getX(),hamburger.getY());
				return true;
			}
			else if(house.isVisible()&&HumanContains(house.getX(), house.getY()) || HumanContains(house.getX() + house.getWidth() - 1, house.getY()) ||  HumanContains(house.getX() + house.getWidth() - 1, house.getY() + house.getHeight() - 1) || HumanContains(house.getX(), house.getY() + house.getHeight() - 1)){
				house.setVisible(false);
				house.setLocation(house.getX(),house.getY());
				return true;
			}
			else if(pants.isVisible()&&HumanContains(pants.getX(), pants.getY()) || HumanContains(pants.getX() + pants.getWidth() - 1, pants.getY()) ||  HumanContains(pants.getX() + pants.getWidth() - 1, pants.getY() + pants.getHeight() - 1) || HumanContains(pants.getX(), pants.getY() + pants.getHeight() - 1)){
				pants.setVisible(false);
				pants.setLocation(pants.getX(),pants.getY());
				return true;
			}
			return false;
		}

		private boolean HumanContains(int x, int y) {
			if (((human.getX() <= x) && (human.getX() + human.getWidth() - 1 >= x)) && ((human.getY() <= y) && (human.getY() + human.getHeight() - 1 >= y))) {
				return true;
			}
			return false;
		}
	}
}
