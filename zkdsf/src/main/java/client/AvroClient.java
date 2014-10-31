package com.zkdsf.clientpool;

import org.apache.avro.ipc.NettyTransceiver;

/**
 * Avro RPC客户端
 * @author sea
 * @param <E>
 */
public class AvroClient<E>
{
	public NettyTransceiver transceiver;
	public E proxy;
}
