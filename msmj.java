import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;




class MisemunjiController{   
   
   public static void main(String args[]) {
    BufferedReader rd;
    try{
	String reqUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=%EB%A7%88%ED%8F%AC%EA%B5%AC&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=4snM6oJ0micT5aKrx8VnWpXPcSNhM7IzFkDb79yPLbOeBoBOGGFhuGyJezZrYrx1H6OZY7r89U4cJsMKoXR1Gg%3D%3D&ver=1.3";
               
	 URL url = new URL(reqUrl);
	 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 conn.setRequestMethod("GET");
	 conn.setRequestProperty("Content-type", "application/xml");
	 
	 if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	 } else {
		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
	 }
	 StringBuilder sb = new StringBuilder();
	 String line;
	 while ((line = rd.readLine()) != null) {
		sb.append(line);
	 }
	 
		System.out.println("000 "+sb);
	 rd.close();
	 conn.disconnect();

	 // XML파싱용 도큐먼트빌드팩토리 생성
	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder = factory.newDocumentBuilder();

	 // 도큐먼트빌트팩토리에 응답 문자열을 넣어줌
	 Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
	 	System.out.println("@@@ "+document);
	 
	 // 노드 리스트 받기
	 //NodeList nodelist = document.getElementsByTagName("body");
	 //System.out.println("111 "+nodelist);
	 //System.out.println(nodelist.getLength());
	 NodeList nodelist2 = document.getElementsByTagName("item");
	 System.out.println("222 "+nodelist2);
	 System.out.println(nodelist2.getLength());
	 System.out.println("Response code: " + conn.getResponseCode());
	System.out.println("getResponseMessage: " + conn.getResponseMessage());
	 for(int i = 0; i < nodelist2.getLength() ; i++) {
	 // NodeList nodelist3 = document.getElementsByTagName("item");
	 // System.out.println("333 "+nodelist3);
		System.out.println("qqqqqq" + nodelist2.item(i));
		
		Element ms = (Element)nodelist2.item(i);
		NodeList dataTimeNode = ms.getElementsByTagName("dataTime");
		NodeList pm10ValueNode = ms.getElementsByTagName("pm10Value");
		Element dataTimeEl = (Element)dataTimeNode.item(0);
		Element pm10ValueEl = (Element)pm10ValueNode.item(0);
		String dataTime = dataTimeEl.getFirstChild().getTextContent();
		String pm10Value = pm10ValueEl.getFirstChild().getTextContent();
		System.out.println("&&& "+dataTime);
		System.out.println("&&& "+pm10Value);
	 }
		
	 /* for(int i = 0; i < nodelist.getLength() ; i++) {
		Element ms = (Element)nodelist.item(i);
		NodeList dataTimeNode = ms.getElementsByTagName("dataTime");
		Element dataTime = (Element)dataTimeNode.item(0);
		
		System.out.println(dataTime);
	 } */  //
 
	}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	 
  }
}