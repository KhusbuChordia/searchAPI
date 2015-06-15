import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.response.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import static org.junit.Assert.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;

public class ApiTest {	
	/* 
	 * Test Case: search_api_1
	 * The api should return 200 response when required 
	 * param is passed 
	 */	
	@Test
	public void searchApi1 () {
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);
			//System.out.println(output);
			List<Map<String, Object>> list = null;
			JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
			Map<String, Object> map = new HashMap<String, Object>();
			if (obj != null) {
				Iterator<String> it = obj.keys();
				while (it.hasNext()) {
					String key = it.next();
					
					if("resultCount".equalsIgnoreCase(key)){
                        System.out.println(obj.get(key));
                        break;
					}
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/* 
	 * Test Case: search_api_2
	 * The api should return 200 when when all optional param's like country, 
	 * media,limit =100 are mentioned 
	 */
	@Test
	public void searchApi2 () {
		try {
			Client client = Client.create();
			int count = 0;
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=US&media=music&limit=100");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);
			List<Map<String, Object>> list = null;
			JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
			Map<String, Object> map = new HashMap<String, Object>();
			if (obj != null) {
				Iterator<String> it = obj.keys();
				while (it.hasNext()) {
					String key = it.next();					
					if("resultCount".equalsIgnoreCase(key)){
                        count = (int) obj.get(key);
                        break;
					}
				}
			}	
			//assert result count
			assertEquals(100,count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * Test Case: search_api_3
	 * The api should return 200 when when one optional param's  limit =100 is mentioned
	 */
	@Test
	public void searchApi3 () {
		try {
			Client client = Client.create();
			int count = 0;
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=US&media=music&limit=100");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);
			List<Map<String, Object>> list = null;
			JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
			Map<String, Object> map = new HashMap<String, Object>();
			if (obj != null) {
				Iterator<String> it = obj.keys();
				while (it.hasNext()) {
					String key = it.next();					
					if("resultCount".equalsIgnoreCase(key)){
                        count = (int) obj.get(key);
                        break;
					}
				}
			}	
			//assert result count
			assertEquals(100,count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * Test Case: search_api_4
	 * The api should return 400 when country code is invalid 
	 */
	@Test
	public void searchApi4 () {
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=Z!");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(400,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		/* 
		 * Test Case: search_api_5
		 * The api should return 400 when media is invalid 
		 */
		@Test
		public void searchApi5 () {
			try {
				Client client = Client.create();
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&media=123");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				//assert response
				assertEquals(400,response.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
		/* 
		 * Test Case: search_api_6
		 * The api should return 200 when limit is invalid default it to 50
		 */
		@Test
		public void searchApi6 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=abc");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				List<Map<String, Object>> list = null;
				JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				Map<String, Object> map = new HashMap<String, Object>();
				if (obj != null) {
					Iterator<String> it = obj.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) obj.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(50,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
		/* 
		 * Test Case: search_api_7
		 * The api should return 200 when limit is greater(greater than 200) default it to 200
		 */
		@Test
		public void searchApi7 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=400");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				List<Map<String, Object>> list = null;
				JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				Map<String, Object> map = new HashMap<String, Object>();
				if (obj != null) {
					Iterator<String> it = obj.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) obj.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(200,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		/* 
		 * Test Case: search_api_8
		 * The api should return 200 when limit is negative(less than 0) default it to 0
		 */
		@Test
		public void searchApi8 () {
			try {
				Client client = Client.create();
				int count = 0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=-18");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				List<Map<String, Object>> list = null;
				JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				Map<String, Object> map = new HashMap<String, Object>();
				if (obj != null) {
					Iterator<String> it = obj.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) obj.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(0,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		
	
		/* 
		 * Test Case: search_api_9
		 * The api should return 200 when term is invalid, resultcount is 0
		 */
		@Test
		public void searchApi9 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool*maynard");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				List<Map<String, Object>> list = null;
				JSONObject obj = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				Map<String, Object> map = new HashMap<String, Object>();
				if (obj != null) {
					Iterator<String> it = obj.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) obj.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(0,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
}
