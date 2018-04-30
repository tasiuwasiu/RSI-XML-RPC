package zadanie;

import java.net.URL;
import org.apache.xmlrpc.*;

/**
 * Klasa obslugujaca zapytania asynchroniczne
 * @author Rafal Wasik
 *
 */
public class AC implements AsyncCallback
{
	/**
	 * Metoda obslugujaca bledne wywolanie asynchroniczne
	 */
	@Override
	public void handleError(Exception arg0, URL arg1, String arg2)
	{
		System.out.println("Blad zapytania asynchronicznego");
		
	}

	/**
	 * Metoda obslugujaca prawidlowe zapytanie asynchroniczne
	 */
	@Override
	public void handleResult(Object arg0, URL arg1, String arg2)
	{
		System.out.println("Zapytanie asynchroniczne ukonczone");
		System.out.println("Wynik: " + arg0.toString());
	}

}
