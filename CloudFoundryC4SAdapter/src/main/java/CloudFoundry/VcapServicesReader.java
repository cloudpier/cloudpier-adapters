package cloudfoundry;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.cloud4soa.adapter.rest.response.model.Database;

/**
 * Utility class to handle VCAP_SERVICES env var.
 * 
 * Usage mode:
 * 
 * vcapstr = System.getenv("VCAP_SERVICES")
 * vcapRootNode = VcapServicesReader.parse(vcapstr)
 * credentials = VcapServicesReader.fillDatabase(vcapRootNode, "mysql-db") 
 * 
 * VCAP_SERVICES sample:
 * 
 * {
 *   "mysql-5.1": [
 *       {
 *           "name": "dbtest2",
 *           "label": "mysql-5.1",
 *           "plan": "free",
 *           "tags": [
 *               "relational",
 *               "mysql-5.1",
 *               "mysql"
 *           ],
 *           "credentials": {
 *               "name": "d7c123396c2d04f0f91ca0fca2ed29ba6",
 *               "hostname": "192.168.252.4",
 *               "host": "192.168.252.4",
 *               "port": 3306,
 *               "user": "uQbdX9gaMX3Yu",
 *               "username": "uQbdX9gaMX3Yu",
 *               "password": "pdhYKBiaSuwVP"
 *           }
 *       },
 *       {
 *           "name": "dbtest",
 *           "label": "mysql-5.1",
 *           "plan": "free",
 *           "tags": [
 *               "relational",
 *               "mysql-5.1",
 *               "mysql"
 *           ],
 *           "credentials": {
 *               "name": "d616cbe71ba2b4b9e92c75c875a12f6bb",
 *               "hostname": "192.168.252.4",
 *               "host": "192.168.252.4",
 *               "port": 3306,
 *               "user": "uoXw2hnx0LGXP",
 *               "username": "uoXw2hnx0LGXP",
 *               "password": "pchJlDzkUnHMV"
 *           }
 *       }
 *   ]
 * }
 * 
 * @author roman.sosa
 *
 */
public class VcapServicesReader {

	static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Parses a json string into a JsonNode. This node is the rootNode for
	 * the rest of methods.
	 * 
	 * @param json json string. 
	 * @return The root JsonNode 
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public static JsonNode parse(String json) throws JsonProcessingException, IOException {
	
		JsonNode result = mapper.readTree(json);
		return result;
	}
	
	/**
	 * Returns the JsonNode corresponding a given service name. 
	 */
	public static JsonNode findBindedService(JsonNode rootNode, String serviceName) {
		
		/*
		 * iterate over services
		 */
		for (JsonNode service : rootNode) {
			/*
			 * iterate over binded services
			 */
			for (JsonNode binded : service) {
				String name = binded.path("name").getValueAsText();
				if (serviceName.equals(name)) {
					return binded;
				}
			}
		}
		return null;
	}
	
	/**
	 * Fill the Database object corresponding a given service name.
	 */
	public static Database fillDatabase(JsonNode rootNode, String serviceName) {
		
		JsonNode binded = findBindedService(rootNode, serviceName);
		if (binded == null) {
			return null;
		}
		JsonNode credentials = binded.path("credentials");
		
		String dbname = credentials.path("name").getTextValue();
		String hostname = credentials.path("hostname").getTextValue();
		int port = credentials.path("port").getValueAsInt();
		String username = credentials.path("username").getTextValue();
		String password = credentials.path("password").getTextValue();
		
		Database result = new Database();
		result.setDatabaseName(dbname);
		result.setHost(hostname);
		result.setPort(String.valueOf(port));
		result.setUserName(username);
		result.setPassword(password);
		
		return result;
		
	}
}
