package goeuro.tests;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class API_Client {

	public static void main(String[] args) {

		//http://api.goeuro.com/api/v2/position/suggest/en/STRING
		
			try {
				 
				Client client = Client.create();
				
				String endpoint = "http://api.goeuro.com/api/v2/position/suggest/en/" + args[0];
		 
				WebResource webResource = client
				   .resource(endpoint);
		 		 
				ClientResponse response = webResource.type("application/json")
				   .get(ClientResponse.class);
		 
				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
		 
				System.out.println("Output from Server .... \n");
				String output = response.getEntity(String.class);
				System.out.println(output);
				
				final JSONArray json_array = new JSONArray(output);
			    
				generateCsvFile(json_array);
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
			  }
	}
	
	private static void generateCsvFile(JSONArray json_array)
	   {
		try
		{
		    System.out.print("Please, enter the destination path and name for the .csv file (i.e: '//testing_files/name_of_file.csv'): ");
		
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		    String path_csv  = br.readLine();
			
			FileWriter writer = new FileWriter(path_csv);
		    
		    writer.append("_id");
		    writer.append(',');
		    writer.append("name");
		    writer.append(',');
		    writer.append("type");
		    writer.append(',');
		    writer.append("latitude");
		    writer.append(',');
		    writer.append("longitude");
		    writer.append('\n');
	 

		    for (int i=0;i<json_array.length();i++){
		    	
		    	writer.append(json_array.getJSONObject(i).get("_id").toString());
			    writer.append(',');
		    	writer.append(json_array.getJSONObject(i).get("name").toString());
			    writer.append(',');
		    	writer.append(json_array.getJSONObject(i).get("type").toString());
			    writer.append(',');
		    	writer.append(json_array.getJSONObject(i).getJSONObject("geo_position").get("latitude").toString());
			    writer.append(',');
			    writer.append(json_array.getJSONObject(i).getJSONObject("geo_position").get("longitude").toString());
			    writer.append('\n');
		    }
	 
		    writer.flush();
		    writer.close();
		    System.out.println("csv file created in: " +path_csv);
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
		catch (JSONException e){
			e.printStackTrace();
		}
	    }
	
}
