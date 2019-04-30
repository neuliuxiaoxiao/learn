package neu.rpc.comsumer.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import neu.rpc.comsumer.service.CalculatorRemoteImpl;
import neu.rpc.provider.service.Calculator;

public class ComsumerApp {
    private static Logger log = LoggerFactory.getLogger(ComsumerApp.class);

    public static void main(String[] args) {
        Calculator calculator = new CalculatorRemoteImpl();
        int result = calculator.add(1, 2);
        log.info("result is {}", result);
    }
}