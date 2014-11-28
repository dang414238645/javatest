package demo.jackson;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class Test {

	public static void main(String[] args) throws IOException {
		ObjectMapper objectMapper=new ObjectMapper();
		JsonEncoding encoding=JsonEncoding.UTF8;
		HttpServletResponse servletResponse=null;
		JsonGenerator jsonGenerator =objectMapper.getJsonFactory().createJsonGenerator(servletResponse.getOutputStream(), encoding);
//				(null, "UTF8");
		objectMapper.writeValue(jsonGenerator, "");
//		objectMapper.readValue(new InputStream(), String.class)

	}

}
