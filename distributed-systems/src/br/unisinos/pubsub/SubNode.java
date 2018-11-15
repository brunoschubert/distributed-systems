package br.unisinos.pubsub;

import org.zeromq.ZMQ;

public class SubNode extends AbstractNode {

//-------------------------------SUBCRIBE ONLY NODE------------------------------------------
	public void startPubSub(int contextSub, String adressSub, String topic) {
		//Starts sub module
		this.subContext = ZMQ.context(contextSub);
		this.subscriber = pubContext.socket(ZMQ.SUB);
		this.subscriber.connect(adressSub);
		//Chose topic to listen to
		this.subscriber.subscribe(topic.getBytes());

	}
	public void stopPubSub() {
		this.subscriber.close ();
		this.subContext.term ();
	}
}

