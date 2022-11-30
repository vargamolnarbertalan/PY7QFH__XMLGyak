package py7qfh;
// Java program to read JSON from a file

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ObjectPY7QFH
{
	public static void main(String[] args) throws Exception
	{
		// 1(a) feladat
		JSONObject input = new JSONObject();
		
		input.put("nev", "Botka Bela");
		input.put("fizetes", "320.000");
		input.put("kor", "21");
		
		System.out.println(input);
		
		// 1(b). feladat
		
		input = new JSONObject();
		
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		keys.add("neptunkod");
		keys.add("nev");
		keys.add("szak");
		
		values.add("PY7QFH");
		values.add("Varga-Molnar Bertalan");
		values.add("GEIK-PI");
		
		for(int i = 0; i < keys.size(); i++) 
		{
			input.put(keys.get(i), values.get(i));
		}
		
		System.out.println(input);
		
		// 2(a) feladat
		
		Object obj = new JSONParser().parse(new FileReader("vizsgakPY7QFH.json"));
		
		System.out.println(obj.toString());
		
		JSONObject jo = (JSONObject) obj;
		JSONObject vizsgak = (JSONObject) jo.get("vizsgak");		
		JSONArray vizsga = (JSONArray) vizsgak.get("vizsga");
		
		System.out.println("--- A JSON fajl tartalma ---");
		System.out.println("ID: " + jo.get("_id"));
		for(int i = 0; i < vizsga.size(); i++) 
		{
			System.out.println(" ");
			JSONObject cursor = (JSONObject) vizsga.get(i);
			System.out.println("kurzus: " + cursor.get("kurzus"));
			System.out.println("helyszin: " + cursor.get("helyszin"));
			System.out.println("idopont");
			JSONObject idopont = (JSONObject) cursor.get("idopont");
			System.out.println("     nap: " + idopont.get("nap"));
			System.out.println("     tol: " + idopont.get("tol"));
			System.out.println("     ig: " + idopont.get("ig"));
			System.out.println("oktato: " + cursor.get("oktato"));
			System.out.println("jegy: " + cursor.get("jegy"));
		}
		
		// 2(b) feladat
		JSONObject output = new JSONObject();
		output.put("_id", "PY7QFH_2");
		output.put("vizsgak", vizsgak);
		
		FileWriter file = new FileWriter("vizsgak1PY7QFH.json");
		file.write(output.toJSONString());
		file.close();
				
	}
}
