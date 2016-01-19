import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class TesteExploradorFila {
	
	public static void main(String[] args) throws Exception{
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");
		
		QueueBrowser browser = session.createBrowser((Queue) fila);
		
		Enumeration<Message> items = browser.getEnumeration();
		
		while (items.hasMoreElements()){
			Message message = items.nextElement();
			System.out.println(((TextMessage) message).getText());
		}
		
		session.close();
		connection.close();
		context.close();
		
		
	}
	
}
