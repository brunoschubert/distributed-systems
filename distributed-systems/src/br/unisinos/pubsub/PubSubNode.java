package br.unisinos.pubsub;

import org.zeromq.ZMQ;

public class PubSubNode extends AbstractNode {
	
//-----------------------------PUBLISH-SUBSCRIBER NODE--------------------------------------
	public void startPubSub(int nodeId, int contextPub, String adressPub, 
			int contextSub, String adressSub, String topic) {
		//TODO Better Id methods
		this.nodeId = nodeId;
		//Starts pub module
		this.pubContext = ZMQ.context(contextPub);
		this.publisher = pubContext.socket(ZMQ.PUB);
		//Binds publishing adress
		this.publisher.bind(adressPub);
		//Starts sub module
		this.subContext = ZMQ.context(contextSub);
		this.subscriber = subContext.socket(ZMQ.SUB);
		//Chose adress to listen to
		this.subscriber.connect(adressSub);
		//Chose topic to listen to
		this.subscriber.subscribe(topic.getBytes());
		
	}
	public void stopPubSub() {
		this.publisher.close();
		this.pubContext.term();
		this.subscriber.close ();
		this.subContext.term ();
	}
}

