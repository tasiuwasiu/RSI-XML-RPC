package Test;

import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;

public class Client
{

	public static void main(String[] args)
	{
		try
		{
			XmlRpcClient srv = new XmlRpcClient("http://localhost:10013");
			Vector <Object> params = new Vector <Object>();
			params.addElement(new Integer(13));
			params.addElement(new Integer(21));
			Object result = srv.execute("mojserver.echo", params);
			
			int wynik = ((Integer) result).intValue();
			System.out.println(wynik);
			
			AC cb = new AC();
			
			Vector<Integer> params2 = new Vector<Integer>();
			params2.addElement(new Integer(1000));
			srv.executeAsync("mojserver.asy", params2, cb);
			System.out.println("Wywolano asynchronicznie");
		}
		catch (Exception e)
		{
			System.err.println("Klient" + e);
		}

		
		
		
	}

}
