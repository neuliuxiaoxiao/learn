package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @Title ClientChannelHandler
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:51
 **/
public class ClientChannelHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment att) {
        ByteBuffer buffer = att.getBuffer();
        if (att.isReadMode()) {
            // ��ȡ���Է���˵�����
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            String msg = new String(bytes, Charset.forName("UTF-8"));
            System.out.println("�յ����Է���˵���Ӧ����: " + msg);

            // ������������������ѡ��:
            // 1. �����˷����µ�����
//            att.setReadMode(false);
//            buffer.clear();
//            String newMsg = "new message from client";
//            byte[] data = newMsg.getBytes(Charset.forName("UTF-8"));
//            buffer.put(data);
//            buffer.flip();
//            att.getClient().write(buffer, att, this);
            // 2. �ر�����
            try {
                att.getClient().close();
            } catch (IOException e) {
            }
        } else {
            // д������ɺ󣬻��������
            att.setReadMode(true);
            buffer.clear();
            att.getClient().read(buffer, att, this);
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("����������Ӧ");
    }
}
