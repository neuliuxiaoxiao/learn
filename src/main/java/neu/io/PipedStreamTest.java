package neu.io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {

	public static void main(String[] args) {
		PipeSender t1 = new PipeSender();
		PipeReceiver t2 = new PipeReceiver();
		PipedOutputStream out = t1.getOutputStream();
		PipedInputStream in = t2.getInputStream();
		try {
			 //�ܵ����ӡ�����2�仰�ı�����һ����
            //out.connect(in);   
            in.connect(out);  
            
            t1.start();
            t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// �����ֽ�����b��
//    synchronized void receive(byte b[], int off, int len)  throws IOException {
//        // ���ܵ�״̬
//        checkStateForReceive();
//        // ��ȡ��д��ܵ������߳�
//        writeSide = Thread.currentThread();
//        int bytesToTransfer = len;
//        while (bytesToTransfer > 0) {
//            // ����д��ܵ�������������ȫ������ȡ�꣬��ȴ���
//            if (in == out)
//                awaitSpace();
//            int nextTransferAmount = 0;//����ܵ��л�ʣ���ٿռ����д������
//            // ������ܵ��б���ȡ�����ݣ�����д��ܵ������ݡ���
//            // ������nextTransferAmount=��buffer.length - in��
//            if (out < in) {
//                nextTransferAmount = buffer.length - in;
//            } else if (in < out) { // ������ܵ��б���ȡ�����ݣ�����/����д��ܵ������ݡ�����ִ�к���Ĳ���
//                // ��in==-1(���ܵ���д�����ݵ��ڱ���ȡ����)����ʱnextTransferAmount = buffer.length - in;
//                // ����nextTransferAmount = out - in;
//                if (in == -1) {//�ܵ��ﻹû������
//                    in = out = 0;//�ȳ�ʼ��Ϊ0
//                    nextTransferAmount = buffer.length - in;// ��ʱ�ܵ�����дbuff.length ����
//                } else {//���� ���������д��֮��  �ڶ���д�Ż��߽����Ĳ��� �൱��ֻ��out-in�Ĳ��ֻ�û��д
//                    nextTransferAmount = out - in;//out֮����Ѿ������� ��ֻ��in��out֮���λ���еط�����д��
//                }
//            }
//            if (nextTransferAmount > bytesToTransfer)
//                nextTransferAmount = bytesToTransfer;
//            // assert���Ե������ǣ���nextTransferAmount <= 0������ֹ����
//            assert(nextTransferAmount > 0);
//            // ������д�뵽������
//            System.arraycopy(b, off, buffer, in, nextTransferAmount);
//            bytesToTransfer -= nextTransferAmount;
//            off += nextTransferAmount;
//            in += nextTransferAmount;
//            if (in >= buffer.length) {
//                in = 0;
//            }
//        }
//    }
}
