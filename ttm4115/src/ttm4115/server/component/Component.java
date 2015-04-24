package ttm4115.server.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public java.lang.String readyMessage = "Ready to serve \n";
	public java.lang.String database = "/home/pi/server/database.txt";

	public com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters init() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("lockStatus");
		return new Parameters(m);
	}
	
	public void printMessage(MQTTMessage m) {
		System.out.println("---------- Received Message ----------");
	    System.out.println("Sent to topic: " + m.getTopic());
	    System.out.println("Payload: " + new String(m.getPayload()));
	    System.out.println("--------------------------------------");
	}

	public void processMessage(MQTTMessage m) {
		String payload =  new String( m.getPayload());
		String[] parts = payload.split(" ");
		int id = Integer.parseInt(parts[0]);
		int status = Integer.parseInt(parts[1]);	
	}
	
	private void saveToDatabase(int id, boolean status) {
		
	}

	public void updateStatus(MQTTMessage update, ArrayList toilets) {
		String payload = new String( update.getPayload());
		String[] parts = payload.split(" ");
		int id = Integer.parseInt(parts[0]);
		int status = Integer.parseInt(parts[1]);
		DateFormat df = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");
		Date dateobj = new Date();
		
		
		System.out.println(df.format(dateobj));
		
	}

	public ArrayList convertToList(String database) {
		ArrayList toilets = new ArrayList();
		
		for (String line : database.split("\n")) {
			String[] room;
			room = line.split(" ");
			
			Toilet toilet = new Toilet();
			toilet.setId(Integer.parseInt(room[0]));
			toilet.setStatus(Integer.parseInt(room[1]));
			toilet.setDescription(room[2]);
			toilet.setTime();
			toilets.add(toilet);
		}
		return toilets;
	}

}
