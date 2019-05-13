package neu.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOutputStreamTest {

	private static final int LEN = 5;
	// ��ӦӢ����ĸ��abcddefghijklmnopqrsttuvwxyz��
	private static final byte[] ArrayLetters = { 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C,
			0x6D, 0x6E, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A };

	public static void main(String[] args) {
		tesByteArrayOutputStream();
	}

	private static void tesByteArrayOutputStream() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(0x41);
		baos.write(0x42);
		baos.write(0x43);
		System.out.printf("baos=%s\n", baos);
		// ��ArrayLetters�����дӡ�3����ʼ�ĺ�5���ֽ�д�뵽baos�С�
		// ����Ӧд�롰0x64, 0x65, 0x66, 0x67, 0x68��������defgh��
		baos.write(ArrayLetters, 3, 5);
		System.out.printf("baos=%s\n", baos);
		// ���㳤��
        int size = baos.size();
        System.out.printf("size=%s\n", size);
        // ת����byte[]����
        byte[] buf = baos.toByteArray();
        String str = new String(buf);
        System.out.printf("str=%s\n", str);
        
        // ��baosд�뵽��һ���������
        try {
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            baos.writeTo((OutputStream)baos2);
            System.out.printf("baos2=%s\n", baos2);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
