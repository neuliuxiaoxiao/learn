package design.decorator;

/**
 * @Title Lemon
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:54
 **/
public class Lemon extends Condiment {
    private Beverage bevarage;

    // ����ܹؼ�����Ҫ�����������ϣ�����Ҫ����û�б�װ�εĺ����̲裬
    // ��ȻҲ���Դ����Ѿ�װ�κõ�â���̲裬����������â�������̲�
    public Lemon(Beverage bevarage) {
        this.bevarage = bevarage;
    }

    @Override
    public String getDescription() {
        // װ��
        return bevarage.getDescription() + ", ������";
    }

    @Override
    public double cost() {
        // װ��
        return bevarage.cost() + 2; // ��������Ҫ 2 Ԫ
    }
}