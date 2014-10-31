package msg;

public class RegisterMsg
{
	private ServeiceInstanceInfo serveiceInstanceInfo;
	
	private RegisterInfo registerInfo;

	public ServeiceInstanceInfo getServeiceInstanceInfo()
	{
		return serveiceInstanceInfo;
	}

	public void setServeiceInstanceInfo(ServeiceInstanceInfo serveiceInstanceInfo)
	{
		this.serveiceInstanceInfo = serveiceInstanceInfo;
	}

	public RegisterInfo getRegisterInfo()
	{
		return registerInfo;
	}

	public void setRegisterInfo(RegisterInfo registerInfo)
	{
		this.registerInfo = registerInfo;
	}
	
	
	
}
