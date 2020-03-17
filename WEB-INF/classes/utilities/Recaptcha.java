
package utilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.URL; 
import javax.json.Json; 
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Recaptcha {

	public static boolean verify_reCaptcha (String reCaptchaResponse) throws IOException {
		if (reCaptchaResponse == null || "".equals(reCaptchaResponse)) {
			return false;
		}
		try{
		URL obj = new URL("https://www.google.com/recaptcha/api/siteverify");
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String post_params = "secret=" + "6LcVcL0UAAAAADaUEl_6tefefjVsJnUEIT314V1c" + "&response=" + reCaptchaResponse;		
		conn.setDoOutput(true);
		DataOutputStream data_output_stream = new DataOutputStream(conn.getOutputStream());
		data_output_stream.writeBytes(post_params);
		data_output_stream.flush();
		data_output_stream.close();		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine); }
		br.close();
		System.out.println(response.toString());		
		JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}

