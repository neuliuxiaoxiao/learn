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
			 //管道连接。下面2句话的本质是一样。
            //out.connect(in);   
            in.connect(out);  
            
            t1.start();
            t2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 接收字节数组b。
//    synchronized void receive(byte b[], int off, int len)  throws IOException {
//        // 检查管道状态
//        checkStateForReceive();
//        // 获取“写入管道”的线程
//        writeSide = Thread.currentThread();
//        int bytesToTransfer = len;
//        while (bytesToTransfer > 0) {
//            // 若“写入管道”的数据正好全部被读取完，则等待。
//            if (in == out)
//                awaitSpace();
//            int nextTransferAmount = 0;//代表管道中还剩多少空间可以写入数据
//            // 如果“管道中被读取的数据，少于写入管道的数据”；
//            // 则设置nextTransferAmount=“buffer.length - in”
//            if (out < in) {
//                nextTransferAmount = buffer.length - in;
//            } else if (in < out) { // 如果“管道中被读取的数据，大于/等于写入管道的数据”，则执行后面的操作
//                // 若in==-1(即管道的写入数据等于被读取数据)，此时nextTransferAmount = buffer.length - in;
//                // 否则，nextTransferAmount = out - in;
//                if (in == -1) {//管道里还没有数据
//                    in = out = 0;//先初始化为0
//                    nextTransferAmount = buffer.length - in;// 此时管道可以写buff.length 数据
//                } else {//否则 这种情况是写满之后  第二次写才会走进来的操作 相当于只有out-in的部分还没有写
//                    nextTransferAmount = out - in;//out之后的已经填满了 ，只有in到out之间的位置有地方可以写了
//                }
//            }
//            if (nextTransferAmount > bytesToTransfer)
//                nextTransferAmount = bytesToTransfer;
//            // assert断言的作用是，若nextTransferAmount <= 0，则终止程序。
//            assert(nextTransferAmount > 0);
//            // 将数据写入到缓冲中
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
