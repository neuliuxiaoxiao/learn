package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @Title AioClient
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:50
 **/
public class AioClient {

     public static void main(String[] args) throws Exception{
         AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
         Future<?> future = client.connect(new InetSocketAddress(8080));
         future.get();
         Attachment att = new Attachment();
         att.setClient(client);
         att.setReadMode(false);
         att.setBuffer(ByteBuffer.allocate(2048));
         byte[] data = "I am obot!".getBytes();
         att.getBuffer().put(data);
         att.getBuffer().flip();

         // �첽�������ݵ������
         client.write(att.getBuffer(), att, new ClientChannelHandler());
         // ������Ϣһ�����˳��������㹻��ʱ�䴦������
         Thread.sleep(2000);
     }
}
