package neu.netty.rpc.server;

public class HelloRpcImpl implements HelloRPC {
    @Override
    public String hello(String name) {
        return "hello," + name;
    }
}