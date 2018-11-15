package br.unisinos.pubsub;

import org.zeromq.ZMQ;

public class PubNode extends AbstractNode {

	//-------------------------------PUBLISH ONLY NODE------------------------------------------
	public void startPublisher(int nodeId, int context, String adress) {
		//TODO Better Id methods
		this.nodeId = nodeId;
		//Starts pub module
		this.pubContext = ZMQ.context(context);
		this.publisher = pubContext.socket(ZMQ.PUB);
		//Binds publishing adress
		this.publisher.bind(adress);
	}
	public void stopPublisher() {
		this.publisher.close();
		this.pubContext.term();
	}

}
