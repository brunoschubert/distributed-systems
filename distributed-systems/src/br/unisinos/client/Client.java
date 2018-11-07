package br.unisinos.client;

import org.zeromq.*;
import org.zeromq.ZMQ.Socket;
import zmq.ZError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Client implements ZThread.IAttachedRunnable {

    @Override
    public void run(Object[] objects, ZContext zContext, Socket pipe) {

      Socket dealer = zContext.createSocket(ZMQ.DEALER);
      dealer.connect("tcp://127.0.0.1:6000");
      dealer.send("fetch");
      long total = 0;   //  Total bytes received
      long chunks = 0;  //  Total chunks received

      while (true) {
        ZFrame frame = ZFrame.recvFrame(dealer);
        chunks++;
        long size = frame.size();
        frame.destroy();
        total += size;
        if (size == 0)
          break;   //  Whole file received
      }
      System.out.printf("%d chunks received, %d bytes\n", chunks, total);
      pipe.send("OK");
    }
  }
}
