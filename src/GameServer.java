import java.net.ServerSocket;
import java.net.Socket;


public class GameServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		
		boolean namecheck = false;
		try{ 
			serverSocket = new ServerSocket(10012);
			while(true){
				Socket socket = serverSocket.accept();
				Thread thread = new ServerThread(socket,namecheck);
				System.out.println("클라이언트가 접속했습니다");
				namecheck = true;
				
				thread.start();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}
