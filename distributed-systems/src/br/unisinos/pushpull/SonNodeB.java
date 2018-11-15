package br.unisinos.pushpull;
import org.zeromq.ZMQ;

//
//  Connects PULL socket to tcp://localhost:5558
//  Collects workloads from ventilator via that socket
//  Connects PUSH socket to tcp://localhost:5560
//  Sends results to sink via that socket
//
public class SonNodeB {

	public static void main (String[] args) throws Exception {
		ZMQ.Context context = ZMQ.context(1);

		//  Socket to receive messages on
		ZMQ.Socket receiver = context.socket(ZMQ.PULL);
		receiver.connect("tcp://*:5558");

		//  Socket to send messages to
		ZMQ.Socket sender = context.socket(ZMQ.PUSH);
		sender.connect("tcp://*:5560");

		//  Process tasks forever
		while (!Thread.currentThread ().isInterrupted ()) {
			
			byte[] recvd = receiver.recv(0);
			String string = new String(recvd);
			
			String s1b = string.substring((string.length()/2));

			//  Do the work
			Thread.sleep(10000);

			//  Send results to sink
			sender.send(s1b);
		}
		sender.close();
		receiver.close();
		context.term();
	}
}