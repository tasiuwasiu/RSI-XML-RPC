package zadanie;

import java.util.Scanner;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;

/**
 * Klasa obslugujaca klienta
 * @author Rafal Wasik 228034
 *
 */
public class Client
{
	static String[] names;
	static String[] desc;
	static int[] numb;
	static String[] async;
	static int x;
	static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		
		XmlRpcClient server;
		String ip = "http://localhost:10013";
		System.out.println("Podaj adres (http://ip:port)");
		ip = sc.nextLine();
		
		try
		{
			server = new XmlRpcClient(ip);
			@SuppressWarnings("unchecked")
			Vector<String> v = (Vector<String>) server.execute("mojserver.show", new Vector<Object>());
			processShow(v);
			runQuestion(server);
			System.out.println("Client out");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("B³¹d po³¹czenia!");
		}
		sc.close();
	}
	
	/**
	 * Metoda przetwarza zapytanie "show" z serwera
	 * @param v Vector zawierajacy informacje o funkcjach
	 */
	private static void processShow(Vector<String> v)
	{
		x = v.size() / 4;
		names = new String[x];
		desc = new String[x];
		numb = new int[x];
		async = new String[x];
		int j=0;
		System.out.println("Dostepne funkcje: ");
		for (int i=0; i<x; i++)
		{
			names[i] = (String)v.get(j);
			desc[i] = (String)v.get(++j);
			numb[i] = Integer.parseInt(v.get(++j).toString());
			async[i] = v.get(++j).toString();
			j++;
			System.out.println("Funkcja " + (i+1) + ": " + names[i]);
			System.out.println(desc[i]);
		}
	}
	
	/**
	 * Metoda wywoluje na serwerze metode podana przez uzytkownika
	 * @param server - Obiekt polaczenia z serwerem XMLRPC
	 */
	private static void runQuestion(XmlRpcClient server)
	{
		int funcNumber=-1;
		do
		{
			
			System.out.println("Podaj nr funkcji (0 aby wyjœæ)");
			funcNumber = sc.nextInt() - 1;
			sc.nextLine();
			AC cb = new AC();
			if (funcNumber !=-1 && funcNumber< x)
			{
				System.out.println(desc[funcNumber]);
				try
				{
					if(async[funcNumber].equals(new String("1")))
					{
						server.executeAsync(getMethod(funcNumber), getParams(numb[funcNumber]), cb);
						System.out.println("Uruchomiono asynchronicznie");
					}
					else
					{
						Object result = server.execute(getMethod(funcNumber), getParams(numb[funcNumber]));
						System.out.println("Wynik: " + result);
					}
				}
				catch (Exception e)
				{
					System.out.println("Blad wykonywania");
					e.printStackTrace();
				}
			}
		}
		
		while (funcNumber!=-1);
		
	}
	
	/**
	 * Metoda zwraca nazwe polaczenia dla metody o podanym numerze
	 * @param numb - numer metody
	 * @return Nazwa polaczenia dla serwera
	 */
	private static String getMethod(int numb)
	{
		return "mojserver." + names[numb];
	}
	
	/**
	 * Metoda zwraca Vector parametrow dla metody
	 * @param count - ilosc parametrow
	 * @return Vector parametrow
	 */
	private static Vector<Object> getParams(int count)
	{
		Vector<Object> vect = new Vector<Object>();
		for (int i=0; i<count; i++)
		{
			
			System.out.println("Podaj argument " + (i+1));
			vect.add(sc.nextLine());
		}
		return vect;
	}

}
