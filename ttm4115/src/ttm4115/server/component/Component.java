package ttm4115.server.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public java.lang.String readyMessage = "Ready to serve \n";
	public Database database = new Database();
	public DateFormat df = new SimpleDateFormat("dd/MM/yy-HH:mm:ss");

	public com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters init() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("lockStatus");
		return new Parameters(m);
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
		if (!payload.contains("reboot")){
			String[] parts = payload.split(" ");
			int id = Integer.parseInt(parts[0]);
			int status = Integer.parseInt(parts[1]);
			Date dateobj = new Date();
			String timestamp  = df.format(dateobj);
			
			database.setStatus(id, status, timestamp);
		}
	}



}
