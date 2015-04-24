package ttm4115.lds.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.arctis.runtime.SingletonData;

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
		}
		else{return false;}
		
	}

	//public MQTTMessage convertButton(SingletonData SD) {
		//byte[] bytes = ((SingletonData) SD).getInstance().getBytes();
		//MQTTMessage m = new MQTTMessage( bytes, "lockStatus");
		//m.setQoS(1);
		//return m;
	//}
	
	public MQTTMessage sendUp() {
		lockStatus = id+" "+"1";
		System.out.println(lockStatus);
		byte[] bytes = lockStatus.getBytes();
		String topic = "lockStatus";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}
	
	public MQTTMessage sendDown() {
		lockStatus = id+" "+"0";
		System.out.println(lockStatus);
		byte[] bytes = lockStatus.getBytes();
		String topic = "lockStatus";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}
	
	public void reportReady() {
		System.out.println("mqtt ready");
	}

//	public MQTTMessage createMessage() {
//		String payload = "Doen er ledig! Kjapp deg!";
//		byte[] bytes = payload.getBytes();
//	    String topic = "lockStatus";
//		MQTTMessage message = new MQTTMessage(bytes, topic);
//		message.setQoS(2);
//		return message;
//	}

	public void printMessage(MQTTMessage m) {
		System.out.println("---------- Received Message ----------");
	    System.out.println("Sent to topic: " + m.getTopic());
	    System.out.println("Payload: " + new String(m.getPayload()));
	    System.out.println("--------------------------------------");
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
