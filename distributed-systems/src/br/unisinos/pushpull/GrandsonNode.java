package br.unisinos.pushpull;
import org.zeromq.ZMQ;

//
//  Connects PULL socket to tcp://localhost:5559
//  Collects workloads from ventilator via that socket
//  Connects PUSH socket to tcp://localhost:5560
//  Sends results to sink via that socket
//
public class GrandsonNode {

	public static void main (String[] args) throws Exception {
		ZMQ.Context context = ZMQ.context(1);

		//  Socket to receive messages on
		ZMQ.Socket receiver = context.socket(ZMQ.PULL);
		receiver.connect("tcp://localhost:5559");
		
		//  Socket to receive messages on
		ZMQ.Socket receiver2 = context.socket(ZMQ.PULL);
		receiver.connect("tcp://localhost:5560");

		//  Process tasks forever
		while (!Thread.currentThread ().isInterrupted ()) {
			String string = new String(receiver.recv(0));
			String string2 = new String(receiver.recv(0));

			Thread.sleep(10000);
			
			System.out.println(string2 + string);

		}
		receiver2.close();
		receiver.close();
		context.term();
	}
}
