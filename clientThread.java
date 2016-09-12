package raspberryPiFinal;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.Socket;

public class clientThread{
	
	private int idnode;
	private String hostnode;
	private int portnode;
	private Socket soc_Client = null;
	
	public clientThread(int idnode, String hostnode, int portnode ){
		this.idnode = idnode;
		this.hostnode = hostnode;
		this.portnode = portnode;
	}

	
	public void connect(String h, int port, Message msg) throws Exception{

		//while (true) {
			try {
				//soc_Client = new Socket(h, port);
				soc_Client = new Socket ("localhost",port);
				ObjectOutputStream output = new ObjectOutputStream(soc_Client.getOutputStream());
				output.writeObject(msg);
				output.flush();
				soc_Client.close();

				//break;
			} catch (ConnectException e) {
				System.out.println("Server " + h + " is not responding");
	            System.out.println("Trying again after 1 sec");
	            long start = System.currentTimeMillis();
	            long end = start +  1000;
	            while (System.currentTimeMillis() < end) {
	            }
	            this.connect(h, port,msg);

			}
	}

}


