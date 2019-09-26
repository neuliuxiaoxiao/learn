package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Title SocketHandler
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:10
 **/
public class SocketHandler implements Runnable {

    private SocketChannel socketChannel;

    public SocketHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int num;
            while ((num=socketChannel.read(buffer))>0){
                buffer.flip();
                byte[] bytes = new byte[num];
                buffer.get(bytes);

                String re = new String(bytes,"UTF-8");
                System.out.println(re);
                ByteBuffer writeBuffer = ByteBuffer.wrap("tttttttttttttttt".getBytes());
                socketChannel.write(writeBuffer);
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
