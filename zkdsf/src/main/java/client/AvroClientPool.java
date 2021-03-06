package client;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接池
 * @author sea
 * @param <E> avro client
 */
public class AvroClientPool<E> extends GenericObjectPool<E> implements ClientPool<E>
{
	private static final Logger logger = LoggerFactory.getLogger(AvroClientPool.class);
	private String name;

	public AvroClientPool(String name, PooledObjectFactory<E> factory, GenericObjectPoolConfig cfg)
	{
		super(factory, cfg);
		this.name = name;
		logger.debug("创建"+ name +"服务客户端连接池");
	}

	@Override
	public E openClient()
	{
		try
		{
			return this.borrowObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void returnClient (E client)
	{
		this.returnObject(client);	
	}

	@Override
	public void clearAll()
	{
		this.clear();
	}

	@Override
	public void destory()
	{
		this.clear();
		if (!this.isClosed())
		{
			this.close();
		}
		logger.debug("销毁连接池" + name);
	}
}
