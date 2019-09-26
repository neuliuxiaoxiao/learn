package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Title AioServer
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:40
 **/
public class AioServer {

     public static void main(String[] args) throws IOException {
         AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));
         Attachment att = new Attachment();
         att.setServer(server);
         server.accept(att, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
             @Override
             public void completed(AsynchronousSocketChannel client, Attachment att) {
                 try {
                     SocketAddress clientAddr = client.getRemoteAddress();
                     System.out.println("收到新连接"+clientAddr);
                     att.getServer().accept(att,this);
                     Attachment newAtt = new Attachment();
                     newAtt.setServer(server);
                     newAtt.setClient(client);
                     newAtt.setReadMode(true);
                     newAtt.setBuffer(ByteBuffer.allocate(2048));
                     client.read(newAtt.getBuffer(),newAtt,new ChannelHandler());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void failed(Throwable exc, Attachment attachment) {
                 System.out.println("accept failed");
             }
         });
         try {
             Thread.currentThread().join();
         } catch (InterruptedException e) {
         }
      }

}
