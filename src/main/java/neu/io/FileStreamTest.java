package neu.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileStreamTest {

	private static final String FileName = "file.txt";

	public static void main(String[] args) {
		testWrite();
		testRead();
	}

	private static void testWrite() {
		try {
			File file = new File(FileName);
			// Ĭ���ǹرա�׷��ģʽ��
			FileOutputStream fileOut1 = new FileOutputStream(file);
			PrintStream out1 = new PrintStream(fileOut1);
			out1.print("abcdefghijklmnopqrstuvwxyz");
			out1.close();
			// ��׷��ģʽ
			FileOutputStream fileOut2 = new FileOutputStream(file, true);
			PrintStream out2 = new PrintStream(fileOut2);
			// ���ļ��С�д��"0123456789"+���з�
			out2.println("0123456789");
			out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testRead() {
		try {
			// ����1���½�FileInputStream����
			// �½��ļ���file.txt����ӦFile����
			File file = new File(FileName);
			FileInputStream in1 = new FileInputStream(file);

			// ����2���½�FileInputStream����
			FileInputStream in2 = new FileInputStream(FileName);

			// ����3���½�FileInputStream����
			// ��ȡ�ļ���file.txt����Ӧ�ġ��ļ���������
			FileDescriptor fdin = in2.getFD();
			// ���ݡ��ļ���������������FileInputStream������
			FileInputStream in3 = new FileInputStream(fdin);

			// ����read()�����ж�ȡһ���ֽ�
			char c1 = (char) in1.read();
			System.out.println("c1=" + c1);

			// ����skip(long byteCount)������4���ֽ�
			in1.skip(25);

			// ����read(byte[] buffer, int byteOffset, int byteCount)
			byte[] buf = new byte[10];
			in1.read(buf, 0, buf.length);
			System.out.println("buf=" + (new String(buf)));

			// ������FileInputStream�������Ӧ��BufferedInputStream
			BufferedInputStream bufIn = new BufferedInputStream(in3);
			// ��ȡһ���ֽ�
			char c2 = (char) bufIn.read();
			System.out.println("c2=" + c2);

			in1.close();
			in2.close();
			in3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
