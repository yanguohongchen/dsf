package msg;

public class RegisterInfo
{

	public RegisterInfo(String owner)
	{
		this.owner = owner;
	}

	private String owner;

	private long datetime = System.currentTimeMillis();

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	public long getDatetime()
	{
		return datetime;
	}

	public void setDatetime(long datetime)
	{
		this.datetime = datetime;
	}

}
