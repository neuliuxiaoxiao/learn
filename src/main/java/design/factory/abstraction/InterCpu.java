package design.factory.abstraction;

/**
 * @Title InterCpu
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:31
 **/
public class InterCpu implements Cpu {

    @Override
    public void cpu() {
        System.out.println("inter cpu");
    }
}
