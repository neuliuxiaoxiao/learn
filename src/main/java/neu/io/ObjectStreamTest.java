package neu.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectStreamTest {
	private static final String TMP_FILE = "box.tmp";

	public static void main(String[] args) {
		testWrite();
		testRead();
	}

	private static void testWrite() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
			out.writeBoolean(true);
			out.writeByte(65);
			out.writeChar('a');
			out.writeInt(20131015);
			out.writeFloat(3.14F);
			out.writeDouble(1.414D);
			// 写入HashMap对象
			HashMap map = new HashMap();
			map.put("one", "red");
			map.put("two", "green");
			map.put("three", "blue");
			out.writeObject(map);
			Box box = new Box("desk", 80, 48);
            out.writeObject(box);

            out.close();
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
	private static void testRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            System.out.printf("boolean:%b\n" , in.readBoolean());
            System.out.printf("byte:%d\n" , (in.readByte()&0xff));
            System.out.printf("char:%c\n" , in.readChar());
            System.out.printf("int:%d\n" , in.readInt());
            System.out.printf("float:%f\n" , in.readFloat());
            System.out.printf("double:%f\n" , in.readDouble());
            // 读取HashMap对象
            HashMap map = (HashMap) in.readObject();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                System.out.printf("%-6s -- %s\n" , entry.getKey(), entry.getValue());
            }
            // 读取Box对象，Box实现了Serializable接口
            Box box = (Box) in.readObject();
            System.out.println("box: " + box);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Box implements Serializable {
    private int width;   
    private int height; 
    private String name;   

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}
/**
 * Box类“支持序列化”。因为Box实现了Serializable接口。
 *
 * 实际上，一个类只需要实现Serializable即可实现序列化，而不需要实现任何函数。
 */
class Box1 implements Serializable {
    private static int width;   
    private transient int height; 
    private String name;   
    //static  和transient的不会被序列化
    public Box1(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }
    private transient Thread thread = new Thread() {
    	          @Override
    	          public void run() {
    	              System.out.println("Serializable thread");
    	          }
    	      };
    private void writeObject(ObjectOutputStream out) throws IOException{ 
        out.defaultWriteObject();//使定制的writeObject()方法可以利用自动序列化中内置的逻辑。 
        out.writeInt(height); 
        out.writeInt(width); 
        //System.out.println("Box--writeObject width="+width+", height="+height);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{ 
        in.defaultReadObject();//defaultReadObject()补充自动序列化 
        height = in.readInt(); 
        width = in.readInt(); 
        //System.out.println("Box---readObject width="+width+", height="+height);
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}