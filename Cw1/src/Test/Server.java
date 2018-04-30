package Test;

import org.apache.xmlrpc.WebServer;

public class Server
{

	public Integer echo (int x, int y)
	{
		return new Integer (x+y);
	}
	
	public static void main (String[] args)
	{
		try 
		{
			System.out.println("Start servera");
			int port = 10013;
			WebServer server = new WebServer(port);
			server.addHandler("mojserver", new Server());
			server.start();
			System.out.println("port:" + port);
		}
		catch (Exception e)
		{
			System.err.println("serwer rpc: " + e);
		}
			
	}
	
	public boolean asy(int x)
	{
		System.out.println("odliczam");
		try
		{
			Thread.sleep(x);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		System.out.println("koniec odliczania");
		return true;
	}
}
