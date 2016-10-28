package comunnication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class Communication {
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {
		Communication http = new Communication();
		
		System.out.println("Testing 1 -  Send address GET request");
		http.sendGet();
	}
	
	
	// Address GET request
	public static String sendGet() throws Exception{
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
		       
		//CONVERTIONS
		JSONObject message = new JSONObject(response.toString());

		// get message on base64 
		String messageValue = new String(message.getString("value"));
		
		return messageValue;

	}
	
	// HTTP POST request
		private void sendPost() throws Exception {

			String url = "http://interntest.herokuapp.com/get?email=carol.mf14@gmail.com";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}
	
}
