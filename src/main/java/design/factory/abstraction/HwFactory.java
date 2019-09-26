package design.factory.abstraction;

/**
 * @Title HwFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:36
 **/
public class HwFactory implements ComputerFactory{

    @Override
    public void makeCpu(){
        new HwCpu().cpu();
    }
    @Override
    public void makeBoard(){
        new HwBoard().board();
    }
}
