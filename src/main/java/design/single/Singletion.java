package design.single;

/**
 * @Title Singletion
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 12:17
 **/
public class Singletion {

    private static  volatile Singletion singletion = null;

    private Singletion() {
    }

    public static Singletion getInstance(){
        if (singletion==null){
            synchronized (Singletion.class){
                if (singletion==null){
                    singletion=new Singletion();
                }
            }
        }
        return singletion;
    }


    //·½·¨2
    private static class Holder{
        private static Singletion instance = new Singletion();
    }
    public static Singletion getInstance2(){
        return Holder.instance;
    }
}
