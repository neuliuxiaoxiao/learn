package neu.io;

import java.io.IOException;
import java.io.PipedOutputStream;

public class PipeSender extends Thread {

	private PipedOutputStream out = new PipedOutputStream();

	public PipedOutputStream getOutputStream() {
		return out;
	}

	@Override
	public void run() {
		//writeShortMessage();
		 writeLongMessage();
	}

	private void writeShortMessage() {
		String strInfo = "this is a short message";
		try {
			out.write(strInfo.getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void writeLongMessage(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<102;i++) {
            sb.append("0123456789");
        }
		sb.append("qwertyuioplkjhgfdsazxcvbnm");
		String str = sb.toString();
		try {
			out.write(str.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
