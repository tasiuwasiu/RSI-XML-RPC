package Test;

import java.net.URL;

import org.apache.xmlrpc.*;

public class AC implements AsyncCallback
{

	@Override
	public void handleError(Exception arg0, URL arg1, String arg2)
	{
		System.out.println("obsluga bledu");
		
	}

	@Override
	public void handleResult(Object arg0, URL arg1, String arg2)
	{
		System.out.println("obsluga zapytania");
	}

}
