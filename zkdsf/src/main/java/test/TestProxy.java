package test;

public class TestProxy
{

	public static void main(String[] args)
	{
		CountProxyD proxy = new CountProxyD();
		Count bookProxy = (Count) proxy.bind(new CountImpl());
		bookProxy.queryCount();
	}

}
