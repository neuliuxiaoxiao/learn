package neu.io;

import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.io.ObjectInputStream;   
import java.io.ObjectOutputStream;   
import java.io.ObjectOutput;   
import java.io.ObjectInput;   
import java.io.Serializable;   
import java.io.Externalizable;   
import java.io.IOException;   
import java.lang.ClassNotFoundException;   
  
public class ExternalizableTest2 { 
    private static final String TMP_FILE = ".externalizabletest2.txt";
  
    public static void main(String[] args) {   
        // ��������ͨ�����л�����
        testWrite();
        // �����л��ġ����󡱶�����
        testRead();
    }
  

    /**
     * ��Box����ͨ�����л������浽�ļ���
     */
    private static void testWrite() {   
        try {
            // ��ȡ�ļ�TMP_FILE��Ӧ�Ķ����������
            // ObjectOutputStream�У�ֻ��д�롰�������ݡ���֧�����л��Ķ���
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(TMP_FILE));
            // ����Box����
            Box2 box = new Box2("desk", 80, 48);
            // ��box����д�뵽���������out�У����൱�ڽ����󱣴浽�ļ�TMP_FILE��
            out.writeObject(box);
            // ��ӡ��Box����
            System.out.println("testWrite box: " + box);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * ���ļ��ж�ȡ�������л���Box����
     */
    private static void testRead() {
        try {
            // ��ȡ�ļ�TMP_FILE��Ӧ�Ķ�����������
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(TMP_FILE));
            // �Ӷ����������У���ȡ��ǰ�����box����
            Box2 box = (Box2) in.readObject();
            // ��ӡ��Box����
            System.out.println("testRead  box: " + box);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * Box��ʵ��Externalizable�ӿ�
 */
class Box2 implements Externalizable {
    private int width;   
    private int height; 
    private String name;   

    public Box2() {
    }

    public Box2(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(width);
        out.writeInt(height);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        width = in.readInt();
        height = in.readInt();
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}