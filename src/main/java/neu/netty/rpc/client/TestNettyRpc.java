package neu.netty.rpc.client;

import neu.netty.rpc.server.HelloNetty;
import neu.netty.rpc.server.HelloRPC;

public class TestNettyRpc {
	public static void main(String[] args) {
		// 第一次远程调用
		HelloNetty helloNetty = (HelloNetty) NettyRpcProxy.create(HelloNetty.class);
		System.out.println(helloNetty.hello());

		// 第二次远程调用
		HelloRPC helloRPC = (HelloRPC) NettyRpcProxy.create(HelloRPC.class);
		System.out.println(helloRPC.hello("RPC"));
	}
}
