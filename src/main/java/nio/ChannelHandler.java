package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @Title ChannelHandler
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/16 9:48
 **/
public class ChannelHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment att) {
        if (att.isReadMode()) {
            // ��ȡ���Կͻ��˵�����
            ByteBuffer buffer = att.getBuffer();
            buffer.flip();
            byte bytes[] = new byte[buffer.limit()];
            buffer.get(bytes);
            String msg = new String(buffer.array()).toString().trim();
            System.out.println("�յ����Կͻ��˵�����: " + msg);

            // ��Ӧ�ͻ������󣬷�������
            buffer.clear();
            buffer.put("Response from server!".getBytes(Charset.forName("UTF-8")));
            att.setReadMode(false);
            buffer.flip();
            // д���ݵ��ͻ���Ҳ���첽
            att.getClient().write(buffer, att, this);
        } else {
            // �����˵�����ͻ���д����Ҳ�����ˣ�����������ѡ��:
            // 1. �����ȴ��ͻ��˷����µ����ݹ���
//            att.setReadMode(true);
//            att.getBuffer().clear();
//            att.getClient().read(att.getBuffer(), att, this);
            // 2. ��Ȼ������Ѿ��������ݸ��ͻ��ˣ��Ͽ���ε�����
            try {
                att.getClient().close();
            } catch (IOException e) {
            }
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        System.out.println("���ӶϿ�");
    }
}
