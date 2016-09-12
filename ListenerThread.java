package raspberryPiFinal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;



public class ListenerThread implements Runnable{
	
	private ServerSocket soc_server;
    public ListenerThread(ServerSocket soc_server){
		this.soc_server = soc_server;
	}
    
	@Override
	public void run() {
		System.out.println("Listener started");
		while (true) {
            try {
				runserver(soc_server.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void runserver(Socket s) {
		try{
			ObjectInputStream input;
		input = new ObjectInputStream(s.getInputStream());
		//while(true){
		Object obj=null;
		try {
			obj = input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (obj instanceof Message) {
			Message msg = (Message) obj;
			if(msg.getType().equals("qbackMsg")){
				System.out.println(msg.getLetter()+msg.getNumber()+msg.getColor());
				mygui.MessageQueue.add(msg);
				
			}
		}
		}
		catch(Exception e){
			//System.out.println("EOF of client message- server restart");
			try {
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
