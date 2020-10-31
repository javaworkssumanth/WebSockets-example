package chat;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/getActivity")
public class SendDataInChunksToBrowser {

	@OnOpen
	public void setUp(Session session) {
			System.out.println("New connection"+session.getId() );
	}

	@OnClose
	public void closeDown(Session session) {
		System.out.println("New connection"+session.getId() );
	}

	@OnMessage
	public void onMessage(String message,Session session) {
		System.out.println("On message from :"+session.getId()+": message" );
		
		dataSource("hello",session);
	}
	public static  void sentDatatoBrowser(Session session,String  response) {
		
		try {
			session.getBasicRemote().sendText(session.getId()+":::"+response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static  void dataSource(String input,Session session) {
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(20000L);
				sentDatatoBrowser(session,"data"+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
