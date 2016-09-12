package raspberryPiFinal;

import java.io.File;
import java.net.ServerSocket;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ServerThread implements Runnable{


	public int servernode;
    public static int number_of_nodes;
    private String[] all_nodes;
    private Message msg;
    
    public ServerThread(Message msg){
		this.msg = msg;
	}
    private void fileparser(String path) throws Exception {
        Scanner scan_path = new Scanner(new File(path));
        String nextLine = scan_path.nextLine().trim();
        while (nextLine.equals("") || nextLine.charAt(0) == '#') {
            nextLine = scan_path.nextLine().trim();
        }
        if (nextLine.contains("#")) {
            number_of_nodes = Integer.parseInt(nextLine.split("#")[0].trim().split("\\s+")[0]);
        } else {
            number_of_nodes = Integer.parseInt(nextLine.trim().split("\\s+")[0]);
        }
		this.all_nodes = new String[number_of_nodes];
		nextLine = scan_path.nextLine().trim();
		while (nextLine.equals("") || nextLine.charAt(0) == '#') {
			nextLine = scan_path.nextLine().trim();
		}
		for (int i = 0; i < number_of_nodes; i++) {
			if (nextLine.contains("#")) {
				this.all_nodes[i] = nextLine.split("#")[0].trim();
			} else {
				this.all_nodes[i] = nextLine.trim();

			}
			nextLine = scan_path.nextLine();
		} 
    }
	@Override
	public void run() {
		try {
			fileparser("C:/Users/PRAVEEN/Desktop/config.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server port: "+ this.servernode);
		for(int i =0;i<number_of_nodes;i++){
			String[] data = all_nodes[i].split(" ");
			System.out.println(data[0]+" "+data[1]+ " "+data[2]);
			clientThread c = new clientThread(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]));
			try {
				c.connect(data[1], Integer.parseInt(data[2]), msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	/*public static void main(String[] args){
		ServerThread s = new ServerThread(1111);
		Thread t = new Thread(s);
		t.start();
	}*/
	
	

}
