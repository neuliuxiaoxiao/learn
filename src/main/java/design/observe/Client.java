package design.observe;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 15:04
 **/
public class Client {
    public static void main(String[] args) {
        // �ȶ���һ������
        Subject subject = new Subject();
        // ����۲���
        new BinaryObserver(subject);
  //      new HexaObserver(subject1);

        // ģ�����ݱ�������ʱ�򣬹۲����ǵ� update �������ᱻ����
        subject.setState(11);
    }
}
