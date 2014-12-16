package demo.neo4j;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class restapi {
    private final static String ROOT_URI="http://localhost:7474/db/data/";
	private static WebResource resource;
	private static ClientResponse response;
	
	private static  void Connection(){
		resource=Client.create().resource(ROOT_URI);
		
		response=resource.get(ClientResponse.class);
		System.out.println(response.toString());
		if(response.getStatus()==200){
			System.out.println("Connected Now...");
		}else{
			System.out.println("Not Connected!!");
		}
	}
	
	
	public static boolean IsConnected(){
		resource=Client.create().resource(ROOT_URI);
		response=resource.get(ClientResponse.class);
		
		if(response.getStatus()==200){
			response.close();
			return true;
		}else{
			response.close();
			return false;
		}
	}
	
	public static void GetAllNodes(){
		if(IsConnected()){
			String post_uri=ROOT_URI+"cypher";
			String CypherQuery="START n=node(*) return n";
			String queryJson="{\"query\":\""+CypherQuery+"\","+
									"\"param\":{}}";	//这里比较难看，就是简单的Json拼凑，可以提出到单独的方法中
			
			resource=Client.create().resource(post_uri);
			response=resource.accept(MediaType.APPLICATION_JSON)
									.type(MediaType.APPLICATION_JSON)
									.entity(queryJson)
									.post(ClientResponse.class);
			
			if(response.getStatus()==200){
				System.out.println("Status OK");
				System.out.println(response.getEntity(String.class));
			}else{
				System.out.println("ERROR "+response.getStatus()+"\n"+response.getEntity(String.class));
			}
		}else{
			System.out.println("Connection Refused...");
			System.exit(0);
		}
	}
	
	
	public static void main(String args[]){
		Connection();
		GetAllNodes();
	}
	
}
