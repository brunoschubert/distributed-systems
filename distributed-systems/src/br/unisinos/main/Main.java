package br.unisinos.main;

import org.zeromq.*;
import org.zeromq.ZMQ.Socket;
import zmq.ZError;

import br.unisinos.server.Server;
import br.unisinos.client.Client;
import br.unisinos.editor.TextEditor;

public class Main {
	private static final int CHUNK_SIZE = 250000;

	//  The main task starts the client and server threads; it's easier
	//  to test this as a single process with threads, than as multiple
	//  processes:
	public static void main(String[] args) {
		ZContext ctx = new ZContext();
		//  Start child threads
		ZThread.fork(ctx, new Server());
		Socket client = ZThread.fork(ctx, new Client());
		//  Loop until client tells us it's done
		client.recvStr();
		//  Kill server thread
		ctx.destroy();
	}
	/*
	 * TODO LIST
	 * Text Editor -
	 * Text Saver  -
	 * Text Reader -
	 * ZMQ Test
	 * ZMQ Pipeline Pattern Test
	 * ZMQ pub/sub Pattern Test
	 *
	 *   TextEditor edit = new TextEditor(); 	
	 */
}
