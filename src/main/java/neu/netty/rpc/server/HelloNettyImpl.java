package neu.netty.rpc.server;

public class HelloNettyImpl implements HelloNetty {
    @Override
    public String hello() {
        return "----> hello,netty <---";
    }
}
