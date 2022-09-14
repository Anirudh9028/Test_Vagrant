package vagrant_test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import org.testng.annotations.*;

public class TestVagrant
{
	
	ArrayList<String>countryname = new ArrayList <String>();
	ArrayList<String>position = new ArrayList <String>();
	
	@BeforeClass
	public void method() throws IOException, ParseException
	{
		JSONParser jp =new JSONParser ();

		FileReader read = new FileReader(".\\jsonfile\\testdata.json");

		Object obj = jp.parse(read);

		JSONObject rcteam = (JSONObject) obj;

		JSONArray playerlist = (JSONArray)rcteam.get("player");

		for(int i =0;i<playerlist.size();i++) 
		{
			JSONObject players =(JSONObject) playerlist.get(i);

			String contryName = (String)players.get("country");
			String role = (String) players.get("role");
			
			countryname.add(contryName);
			position.add(role);

		}
		

	}
	
	@Test
	public void FourForeginPlayer()
	{
		int Countrycount = 0;
		
		for(String ele:countryname) 
		{
			if(!ele.equalsIgnoreCase("india")) 
			{
				Countrycount =Countrycount+1;
				
			}
		}
		
		int actualCountryCount = Countrycount;
		int exceptCountryCount = 4;
		
		Assert.assertEquals(actualCountryCount,exceptCountryCount);
			
	}
	
	
	
	
	@Test
	public void OneWicketKeeper () 
	{
		int roleCount = 0;
		
		
		for(String jk:position ) 
		{
			if(jk.equalsIgnoreCase("Wicket-keeper"))
			{
				roleCount =roleCount+1;
				
			}
		}
		
		
		int actualroleCount = roleCount;
		int exceptroleCount = 1;
		Assert.assertEquals(actualroleCount,exceptroleCount );		
		
	}
	
}
