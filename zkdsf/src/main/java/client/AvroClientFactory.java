package client;

import java.net.InetSocketAddress;

import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * avro rpc 客户端工厂
 * 
 * @author sea
 */
public final class AvroClientFactory<E> extends BasePooledObjectFactory<E>
{
	private static final Logger logger = LoggerFactory.getLogger(AvroClientFactory.class);

	private String hostname;
	private int port;
	private Class<E> clazz;

	private NettyTransceiver client;

	public AvroClientFactory(Class<E> clazz, String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
		this.clazz = clazz;
	}

	@Override
	public E create() throws Exception
	{
		try
		{
			client = new NettyTransceiver(new InetSocketAddress(hostname, port));
			E proxy = SpecificRequestor.getClient(clazz, client);

			logger.debug(clazz.getSimpleName() + "客户端连接池创建实例" + proxy);
			return proxy;
		} catch (Exception e)
		{
			logger.error(clazz.getSimpleName() + "客户端连接池创建客户端错误:" + e.getMessage(), e);
			throw e;
		}
	}


	@Override
	public void destroyObject(PooledObject<E> p) throws Exception
	{
		client.close();
		logger.debug(clazz.getSimpleName() + "客户端连接池销毁实例" + p.getObject());
	}

	@Override
	public PooledObject<E> wrap(E obj)
	{
		return new DefaultPooledObject<E>(obj);
	}
}
