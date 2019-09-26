package design.factory.abstraction;

/**
 * @Title HwCpu
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:32
 **/
public class HwCpu implements Cpu {

    @Override
    public void cpu() {
        System.out.println("Hw Cpu");
    }
}
