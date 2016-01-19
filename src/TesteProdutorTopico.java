import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorTopico {
	
	public static void main(String[] args) throws Exception{
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("loja");
		MessageProducer producer = session.createProducer(topico);
		Message message = session.createTextMessage("<pedido><id>1</id></pedido>");
		message.setBooleanProperty("ebook",false);
		producer.send(message);
		Message message2 = session.createTextMessage("<pedido><id>2</id></pedido>");
		producer.send(message2);
		
		session.close();
		connection.close();
		context.close();
		
		
	}
	
}
