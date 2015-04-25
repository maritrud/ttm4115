package ttm4115.lds.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {
	
	public java.lang.String id = "001";
	public java.lang.String lockStatus = id+" "+"1";
	public com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters init() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("lockStatus");
		return new Parameters(m);
	}

	
	public boolean serverReboot(MQTTMessage m){
		String info = new String(m.getPayload());
		System.out.println(info);
		if (info.contains("reboot")){
			return true;
		} else {return false;}
	}
	
	public MQTTMessage sendUp() {
		lockStatus = id+" "+"1";
		byte[] bytes = lockStatus.getBytes();
		String topic = "lockStatus";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(1);
		return message;
	}
	
	public MQTTMessage sendDown() {
		lockStatus = id+" "+"0";
		byte[] bytes = lockStatus.getBytes();
		String topic = "lockStatus";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

	public void ready() {
		System.out.println("Ready for use.\n");
	}


	public MQTTMessage sendStatus() {
		byte[] bytes = lockStatus.getBytes();
		String topic = "lockStatus";
		System.out.println("send status");
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
		
	}

}
