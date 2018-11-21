package br.unisinos.pubsub;

import java.util.UUID;

import org.zeromq.ZMQ;

public class SubNode extends AbstractNode {

//-------------------------------SUBCRIBE ONLY NODE------------------------------------------
	public void startSub(UUID nodeId, int context, String adress, String topic) {
		//TODO Better Id methods
		this.nodeId = nodeId;
		//Starts sub module
		this.subContext = ZMQ.context(context);
		this.subscriber = subContext.socket(ZMQ.SUB);
		this.subscriber.connect(adress);
		//Chose topic to listen to
		this.subscriber.subscribe(topic.getBytes());

	}
	public void stopSub() {
		this.subscriber.close ();
		this.subContext.term ();
	}
}

