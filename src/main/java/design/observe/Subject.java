package design.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title Subject
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 15:03
 **/
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // �����ѱ����֪ͨ�۲�����
        notifyAllObservers();
    }

    // ע��۲���
    public void attach(Observer observer) {
        observers.add(observer);
    }

    // ֪ͨ�۲�����
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
