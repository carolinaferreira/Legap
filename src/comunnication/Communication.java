package comunnication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Communication {
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {
		System.out.println("Testing 1 -  Send address GET request");
		Communication.sendGet();
		
	}
	
	// GET request
	public static JSONObject sendGet() throws Exception{
		String url = "http://interntest.herokuapp.com/get?email=carol.mf14@gmail.com";
		
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		connection.setRequestMethod("GET");
	
		connection.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL: "+ url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
		
		String inputLine;
		
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		       
		JSONObject message = new JSONObject(response.toString());
		
		return message;

	}
	
	// POST request
	public static void sendPost(JSONObject object) throws Exception {
		String url = "https://interntest.herokuapp.com/post";
		URL obj = new URL(url);
		
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestMethod("POST");

		OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		wr.write(object.toString());
		wr.flush();

		//show what returns the POST request
		
		int responseCode = connection.getResponseCode();
		
		System.out.println("\nSending 'POST' request to URL: "+ url);
		System.out.println("Response Code : " + responseCode);
		
	}
}