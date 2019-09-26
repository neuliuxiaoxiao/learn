package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Title SocketChannelTest
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:15
 **/
public class SocketChannelTest {

     public static void main(String[] args) throws IOException {
         SocketChannel socketChannel = SocketChannel.open();
         socketChannel.connect(new InetSocketAddress("localhost",8080));
         ByteBuffer buffer = ByteBuffer.wrap("123123123".getBytes());
         socketChannel.write(buffer);
         ByteBuffer readBuffer = ByteBuffer.allocate(1024);
         int num;
         if((num=socketChannel.read(readBuffer))>0){
             readBuffer.flip();
             byte[] re = new byte[num];
             readBuffer.get(re);
             String result = new String(re,"UTF-8");
             System.out.println("∑µªÿ÷µ£∫"+result);
         }
      }
}
