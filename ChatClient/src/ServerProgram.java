import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//Server
public class ServerProgram {
	public static void main(String[] args) throws IOException{
		List<ClientSocket> clients = new ArrayList<ClientSocket>();
		Socket sock;
		
		ServerSocket svrSocket = new ServerSocket(10009);
		Date date = new Date();
		System.out.println("server started... at " + date.toString());
		
		while(true)
		{
			sock = svrSocket.accept();
			System.out.println("connected... from" + sock.getRemoteSocketAddress().toString());
			
			ClientSocket clt = new ClientSocket(sock);	
			clt.setReceiveListener(new ReceiveListener(){
				@Override
				public void OnReceive(String echo) {
		               String[] datas = echo.split("#");
		               
		               String msg = String.format("%s:%s\n", datas[0], datas[1]);
		               System.out.printf("echo data : " + echo);
		               
		               for(ClientSocket c:clients)
		                  clt.send(echo);
		               
		               if (echo.equals("bye")) {
		                  clt.close();
		               }

		            }
			});
			clients.add(clt);
		}
	}
}
