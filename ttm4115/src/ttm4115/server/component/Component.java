package ttm4115.server.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public java.lang.String readyMessage = "Ready to serve \n";
	public Database database = new Database();

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

	public MQTTMessage reboot() {
		String rebootMessage = "reboot";
		System.out.println(rebootMessage);
		byte[] bytes = rebootMessage.getBytes();
		String topic = "lockStatus";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(1);
		return message;
	}

	public void updateToilet(MQTTMessage m) {
		String payload = new String( m.getPayload());
		String[] parts = payload.split(" ");
		int id = Integer.parseInt(parts[0]);
		boolean status = (Integer.parseInt(parts[1]) != 0);
		String timestamp = parts[2];
		
		database.setStatus(id, status, timestamp);
	}



}
