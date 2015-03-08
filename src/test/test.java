package test;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@SuppressWarnings("unused")
public class test {
	
	Document doc;
	
	static Element bDay = null;
	static Element dDay = null;
	
	static String bText = "";
	static String dText = "";
	
	public static void parse( Document doc ){
		
		try {
			bDay = doc.select(".bday").first();
			bText = bDay.text();
//			System.out.println("bday");
		}
		catch( NullPointerException bday ){
			try {
				bDay = doc.select("div.navhead").first();
				bText = bDay.text();
//				System.out.println("navhead");
			}
			catch ( NullPointerException navhead ){
				try {
					bDay = doc.select("tr td ul li").first();
					bText = bDay.text();
					if(	!(
							bText.contains("0") ||
							bText.contains("1") ||
							bText.contains("2") ||
							bText.contains("3") ||
							bText.contains("4") ||
							bText.contains("5") ||
							bText.contains("6") ||
							bText.contains("7") ||
							bText.contains("8") ||
							bText.contains("9")))
					{
						int count = 0;
						bDay = doc.select(".infobox tbody tr td:eq("+count+") ul li").first();
						bText = bDay.text();
						System.out.println(count);
						while(	!(
								bText.contains("0") ||
								bText.contains("1") ||
								bText.contains("2") ||
								bText.contains("3") ||
								bText.contains("4") ||
								bText.contains("5") ||
								bText.contains("6") ||
								bText.contains("7") ||
								bText.contains("8") ||
								bText.contains("9")))
						{
							count++;
							System.out.println(count);
							bDay = doc.select(".infobox tbody tr td:eq("+count+") ul li").first();
							bText = bDay.text();
						}
						count = 0;
						while(	!(
								bText.contains("0") ||
								bText.contains("1") ||
								bText.contains("2") ||
								bText.contains("3") ||
								bText.contains("4") ||
								bText.contains("5") ||
								bText.contains("6") ||
								bText.contains("7") ||
								bText.contains("8") ||
								bText.contains("9")))
						{
							count++;
							System.out.println(count);
							bDay = doc.select(".infobox tbody tr:eq("+count+") td").first();
							bText = bDay.text();
						}
					}
					
				}
				catch ( NullPointerException br ){
					bText = "N/A";
//					System.out.println("Date of birth not found");
				}
			}
		}
		
		try {
			dDay = doc.select(".dday").first();
			dText = dDay.text();
		}
		catch( NullPointerException dday ){
			try {
				dDay = doc.select(".dtend").first();
				dText = dDay.text();
			}
			catch( NullPointerException dtend )
			{
				dText = "N/A";
//				System.out.println("Date of death not found");
			}
		}
	}
	
	public static String getQuery(){
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Query: ");
		String query = scan.next();
		
		return query.replace('-', '_');
//		return editQuery(query);
	}

	public static void main(String[] args) throws IOException {
		
		String query = getQuery();
		
		Document doc = Jsoup.connect("http://en.wikipedia.org/wiki/" + query).get();
		
		parse(doc);
		
		System.out.println("Start: " + bText);
		System.out.println("End: " + dText);
	}
}
