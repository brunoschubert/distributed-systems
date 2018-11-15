package br.unisinos.pushpull;
import org.zeromq.ZMQ;

//
//  Connects PULL socket to tcp://localhost:5558
//  Collects workloads from ventilator via that socket
//  Connects PUSH socket to tcp://localhost:5559
//  Sends results to sink via that socket
//
public class SonNodeA {

	public static void main (String[] args) throws Exception {
		ZMQ.Context context = ZMQ.context(1);

		//  Socket to receive messages on
		ZMQ.Socket receiver = context.socket(ZMQ.PULL);
		receiver.connect("tcp://localhost:5558");

		//  Socket to send messages to
		ZMQ.Socket sender = context.socket(ZMQ.PUSH);
		sender.connect("tcp://localhost:5559");

		//  Process tasks forever
		while (!Thread.currentThread ().isInterrupted ()) {
			System.out.println("AY");
			String string = new String(receiver.recv(0));
	
			String s1a = string.substring(0, (string.length()/2));
			
			System.out.println(s1a);
			//  Do the work
			Thread.sleep(1000);

			//  Send results to sink
			sender.send(s1a);
		}
		sender.close();
		receiver.close();
		context.term();
	}
}