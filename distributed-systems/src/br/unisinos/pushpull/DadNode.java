package br.unisinos.pushpull;
import org.zeromq.ZMQ;

//
//  Connects PUSH socket to tcp://localhost:5558
//  Sends results to sink via that socket
//
public class DadNode {

    public static void main (String[] args) throws Exception {
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to send messages to
        ZMQ.Socket sender = context.socket(ZMQ.PUSH);
        sender.connect("tcp://*:5558");

        //  Process tasks forever
        while (!Thread.currentThread ().isInterrupted ()) {
        	
            String str = "Tis' But a scratch";
            System.out.println(str);
        	Thread.sleep(5000);
        	//Creates a String and send to adjacent node
            sender.send(str.getBytes());
            
        }
        sender.close();
        context.term();
    }
}