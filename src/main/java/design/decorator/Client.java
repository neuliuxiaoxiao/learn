package design.decorator;

import com.mongodb.Mongo;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:55
 **/
public class Client {
    public static void main(String[] args) {
        // ���ȣ�������Ҫһ���������ϣ���衢�̲�򿧷�
        Beverage beverage = new GreenTea();
        // ��ʼװ��
        beverage = new Lemon(beverage); // �ȼ�һ������

        System.out.println(beverage.getDescription() + " �۸񣺣�" + beverage.cost());
        //"�̲�, ������, ��â�� �۸񣺣�16"
    }
}
