package neu.jvm;

/**
 * @ClassName jvm
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/7/15 8:48
 **/
public class SafepointTest {
    static double sum = 0;

    public static void foo() {
        for (int i = 0; i < 0x77777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50000000; i++) {
            new Object().hashCode();
        }
    }
    public static void main(String[] args) {
        new Thread(SafepointTest::foo).start();
        new Thread(SafepointTest::bar).start();
    }
}
