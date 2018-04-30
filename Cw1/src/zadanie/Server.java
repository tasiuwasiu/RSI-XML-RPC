package zadanie;


import java.util.Date;
import org.apache.xmlrpc.WebServer;

/**
 * Klasa obslugujaca serwer
 * @author Rafa³ Wasik 228034
 *
 */
public class Server
{
	static String[] methods;

	public static void main (String[] args)
	{
		initMethods();
		try
		{
			System.out.println("Start servera");
			int port = 10013;
			WebServer server = new WebServer (port);
			server.addHandler("mojserver", new Server());
			server.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda inicjuje tabele dostepnych metod
	 */
	private static void initMethods()
	{
		methods = new String[12];
		methods[0] ="delta";
		methods[1] = "oblicz delte (int a, int b, int c)";
		methods[2] = "3";
		methods[3] = "0";
		methods[4] = "trygon";
		methods[5] = "oblicz funkcje trygonometryczne (String func, double kat, int czas)";
		methods[6] = "3";
		methods[7] = "1";
		methods[8] = "data";
		methods[9] = "zwroc dzisiejsza date ()";
		methods[10] = "0";
		methods[11] = "0";
		
	}
	
	/**
	 * Metoda zwraca tablice wszystkich metod
	 * @return Tablica metod
	 */
	public String[] show()
	{
		return methods;
	}
	
	/**
	 * Metoda oblicza wartosc delty na podstawie wzoru (b^2 - 4ac)
	 * @param a - parametr a
	 * @param b - parametr b
	 * @param c - parametr c 
	 * @return Wartosc funkcji delty
	 */
	public Integer delta (String a, String b, String c)
	{
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		int z = Integer.parseInt(c);
		return new Integer ((y*y) - (4*x*z));
	}
	
	/**
	 * Metoda oblicza wartosc funkcji trygonometrycznej dla danego kata po danym czasie
	 * @param typ - Rodzaj funkcji trygonometrycznej
	 * @param kat - Kat dla podanej funkcji w stopniach
	 * @param czas - Czas po jakim ma zosta wykonana metoda w sekundach
	 * @return Zwraca wartosc wykonanej funkcji
	 */
	public String trygon(String typ, String kat, String czas)
	{
		double k =Math.toRadians(Double.parseDouble(kat));
		System.out.println(k);
		int c = Integer.parseInt(czas);
		try
		{
			Thread.sleep(c*1000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		double temp=0.0;
		System.out.println("koniec odliczania");
		if (typ.equals("tan"))
			temp = Math.tan(k);
		if (typ.equals("ctg"))
			temp = 1.0/Math.tan(k);
		if (typ.equals("sin"))
			temp = Math.sin(k);
		if (typ.equals("cos"))
			temp = Math.cos(k);
		System.out.println(temp);
		return String.format("%.2f", temp);
	}
	
	/**
	 * Metoda zwraca aktualna date
	 * @return String z aktualna data
	 */
	public String data()
	{
		Date d = new Date();
		return d.toString();
	}
	
}
