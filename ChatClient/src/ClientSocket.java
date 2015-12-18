import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//스레드 //클라이언트
public class ClientSocket extends Thread{
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private Scanner nscan;
	private PrintWriter nout;
	
	private ReceiveListener receiveListener;
	private boolean isRun = true;
	
	public ClientSocket(String ip, int port) throws UnknownHostException, IOException{//접속
		socket = new Socket(ip, port);
		System.out.println("Connected... to "+socket.getRemoteSocketAddress().toString());
		
		init(socket);
	}
	
	
	
	public void setReceiveListener(ReceiveListener receiveListener) {
		this.receiveListener = receiveListener;
	}



	private void init(Socket socket) throws IOException {
		this.socket = socket;
		is = socket.getInputStream();
		nscan = new Scanner(is);
		os = socket.getOutputStream();
		nout = new PrintWriter(os,true);
		
		this.start(); // 스타트하는 동시 런 시작!
		
	}

	public ClientSocket(Socket socket) throws IOException {
		init(socket);
	}
	@Override
	public void run() {
		
		//이부분을 지금 결정할 수 없다 지금 이 소켓 캡슐을 이용하는 애가 여기를 채워라
		/*Scanner scan = new Scanner(System.in);
		System.out.print("Msg : ");
		String msg = scan.nextLine();
		nout.println(msg);
		
		System.out.println("echo data : " + echo);*/
		
		
		while(isRun){
			String echo = nscan.nextLine();
			if(receiveListener != null)
				receiveListener.OnReceive(echo);
			
		}
		
		//데이터가 보내지면 close
		nout.close();
		nscan.close();
		try {
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void send(String msg) {
		// TODO Auto-generated method stub
		nout.println(msg);
	}



	public void close() {
		// TODO Auto-generated method stub
		isRun = false;		
	}

}
