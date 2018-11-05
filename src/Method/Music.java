package Method;

import java.io.*;
import javazoom.jl.player.*;

public class Music {
	public Music(){
		try{
			Player p=new Player(new FileInputStream("Friday.mp3"));
			p.play();
			p.close();
		}catch(Exception e){}
	}

}