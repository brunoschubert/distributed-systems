package br.unisinos.server;

import org.zeromq.*;
import org.zeromq.ZMQ.Socket;
import zmq.ZError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Server implements ZThread.IAttachedRunnable {

    @Override
    public void run(Object[] objects, ZContext zContext, Socket socket) {
      File file = new File("testdata");
      FileInputStream fr;
      try {
        fr = new FileInputStream(file);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return;
      }
      Socket router = zContext.createSocket(ZMQ.ROUTER);
      //  Default HWM is 1000, which will drop messages here
      //  because we send more than 1,000 chunks of test data,
      //  so set an infinite HWM as a simple, stupid solution:
      router.setHWM(0);
      router.bind("tcp://*:6000");
      while (!Thread.currentThread().isInterrupted()) {
        //  First frame in each message is the sender identity
        ZFrame identity;
        try {
          identity = ZFrame.recvFrame(router);
        } catch (ZMQException e) {
          if (e.getErrorCode() == ZError.ETERM)
            break;
          e.printStackTrace();
          break;
        }

        //  Second frame is "fetch" command
        String command = router.recvStr();
        assert ("fetch".equals(command));
        byte[] data = new byte[CHUNK_SIZE];
        int size;
        while (true) {
          try {
            if (fr.available() <= CHUNK_SIZE)
              size = fr.read(data);
            else
              size = fr.read(data, 0, CHUNK_SIZE);
          } catch (IOException e) {
            e.printStackTrace();
            break;
          }
          ZFrame chunk = new ZFrame(Arrays.copyOf(data, size < 0 ? 0 : size));
          identity.send(router, ZMQ.SNDMORE);
          chunk.sendAndDestroy(router, 0);
          if (size <= 0)
            break;    //  Always end with a zero-size frame
        }
        identity.destroy();
      }
      try {
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
}
