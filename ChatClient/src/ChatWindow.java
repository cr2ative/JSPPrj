import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class ChatWindow extends Frame {
   private ClientSocket socket;
   
   private MenuBar mainMenu;
   private Menu mnFile;
   private MenuItem miFileConnect;
   private MenuItem miFileExit;
   
   private TextArea outputText;
   
   private Panel inputPanel;
   private TextField inputText;
   private Button sendButton;
   
   //채팅을 위한 사용자 식별 값들?
   private String nicName="충희오빵";
   
   public ChatWindow() {
	   mainMenu = new MenuBar();
	   mnFile = new Menu("File");
      
	   miFileConnect = new MenuItem("접속");
	   miFileConnect.addActionListener(new ActionListener() {
         
		   @Override
		   public void actionPerformed(ActionEvent e) {
            try {
               socket = new ClientSocket("211.238.142.102", 10009);
               outputText.setText("connected to..");
               socket.setReceiveListener(new ReceiveListener() {
            
            @Override
            public void OnReceive(String echo) {
            	String[] datas = echo.split("#");
                String txt = outputText.getText();
                txt +="\n\r" + String.format("[%s]:%s:%s",datas[0],datas[1]);
                outputText.setText(txt);
            }
         });             
          
               
            } catch (UnknownHostException e1) {
               e1.printStackTrace();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      });
      
      miFileExit = new MenuItem("Exit");
      miFileExit.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });   
      
      mnFile.add(miFileConnect);
      mnFile.add(miFileExit);
      mainMenu.add(mnFile);
      
      outputText = new TextArea();
      
      inputPanel = new Panel();
      inputPanel.setPreferredSize(new Dimension(10, 70));
      inputPanel.setLayout(new FlowLayout());
      inputText = new TextField();
      inputText.setPreferredSize(new Dimension(300, 60));
      sendButton = new Button("Send");
      sendButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
    	 //닉네임을 어떻게 포함시키고 구별? 메시지와?
    	 //데이터구분
         socket.send(inputText.getText());
         
      }
   });
      
      this.inputPanel.add(inputText);
      this.inputPanel.add(sendButton);
      
      
      this.setMenuBar(mainMenu);
      this.setLayout(new BorderLayout());
      this.add(outputText, BorderLayout.CENTER);
      this.add(inputPanel, BorderLayout.PAGE_END);
      
      this.setSize(400, 600);
   }

   
   
   /****************************************************/
   public static void main(String[] args) {
      ChatWindow win = new ChatWindow();
      win.setVisible(true);
   }
}