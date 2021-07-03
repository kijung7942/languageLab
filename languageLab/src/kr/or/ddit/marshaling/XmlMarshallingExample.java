package kr.or.ddit.marshaling;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FactorialVO;

public class XmlMarshallingExample {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
		
		FactorialVO target = new FactorialVO(10);
		
		JAXBContext context = JAXBContext.newInstance(FactorialVO.class);
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8"); // 마샬링의 설정 : 인코딩 설정
//		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 마샬링의 설정 : 띄어쓰기
//		
//		marshaller.marshal(target, System.out);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try(
				InputStream is = XmlMarshallingExample.class.getResourceAsStream("factorial.xml");
			){
		
				FactorialVO resultObj = (FactorialVO)unmarshaller.unmarshal(is);
				System.out.println(resultObj);
		}
		
	}
	
	
	public static void marshallingToJson(FactorialVO target) throws JsonParseException, JsonMappingException, IOException  {
		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(System.out, target);
		try(
				InputStream src = XmlMarshallingExample.class.getResourceAsStream("factorial.json");
				){
			FactorialVO resultObj = mapper.readValue(src, FactorialVO.class);
			System.out.println(resultObj.toString());
		}
	}
}
