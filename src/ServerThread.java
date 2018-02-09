
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class ServerThread extends Thread {
	static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());
	Socket Socket;
	PrintWriter writer;
	Random r = new Random();
	boolean namecheck;

	ServerThread(Socket Socket, boolean namecheck) {
		this.Socket = Socket;
		this.namecheck = namecheck;
	
		try {
			writer =  new PrintWriter(Socket.getOutputStream());
			list.add(writer);
			
			} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

	public void run() {
		
	HashMap<String,Integer> map = new HashMap<String,Integer>();

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
				String name = null;
			
							
				System.out.println(list.size()+"님이 들어오셨습니다");
				
			while(true){
				if(list.size() == 1){writer.println("p1");
									writer.flush();
									name = "p1";
									break;}
				if(list.size() == 2){writer.println("p2");
									writer.flush();
									name = "p2";
									sendAll("start");
									break;}}          // 2p의 연결을 기다리기 위한 와일문
					
			 			
				while (true) {
				
				String str = reader.readLine();
				if (str == null){
					
					break;}
				str=str.substring(1, str.length()-1);
				String[] first = str.split(", ");
				for(int i= 0; i < first.length; i++){
					String[] second = first[i].split("=");
					map.put(name+second[0],Integer.parseInt(second[1]));
					
				}
					
				
					//	System.out.println(map.clone());	
			sendAll(map);
						
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			list.remove(writer);
				try {
				Socket.close();
				  System.out.println("하 시발");
			} catch (Exception ignored) {
			}
		}
	}
	private void sendAll(String str){
		for (PrintWriter writer : list) {
			
				writer.println(str);
				writer.flush();
			
		}
	}

	private void sendAll(HashMap<String,Integer> map){
		for (PrintWriter writer : list) {
			
				writer.println(map);
				writer.flush();
			
		}
	}

}