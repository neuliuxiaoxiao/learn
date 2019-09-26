package design.factory.abstraction;

/**
 * @Title InterFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:33
 **/
public class InterFactory implements ComputerFactory{

    @Override
    public void makeCpu(){
        new InterCpu().cpu();
    }
    @Override
    public void makeBoard(){
        new InterBoard().board();
    }
}
