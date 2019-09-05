package neu.netty.rpc.client;

import neu.netty.rpc.server.HelloNetty;
import neu.netty.rpc.server.HelloRPC;

public class TestNettyRpc {
	public static void main(String[] args) {
		// ��һ��Զ�̵���
		HelloNetty helloNetty = (HelloNetty) NettyRpcProxy.create(HelloNetty.class);
		System.out.println(helloNetty.hello());

		// �ڶ���Զ�̵���
		HelloRPC helloRPC = (HelloRPC) NettyRpcProxy.create(HelloRPC.class);
		System.out.println(helloRPC.hello("RPC"));
	}
}
