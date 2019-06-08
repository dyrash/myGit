package kr.co.seoulit.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.to.DustStatusTO;
import kr.co.seoulit.sys.to.ParticulateMatterTo;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nexacro.xapi.data.PlatformData;

@Controller
public class DustStatusController {
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("sys/findStationList.do")
	public void findStationList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String station = inData.getVariable("stationName").getString();
		List<ParticulateMatterTo> pmList = findParticulateMatterStatus(station);
		try{
			datasetBeanMapper.beansToDataset(outData, pmList, ParticulateMatterTo.class);
		}catch(Exception e) {
			System.out.println("@@@@@" + e.getMessage());
		}
	}

	public List<ParticulateMatterTo> findParticulateMatterStatus(String station) throws Exception {
		Document doc = null;
		boolean validation = false;
		List<ParticulateMatterTo> pmList = new ArrayList<>();

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(validation);
			dbf.setNamespaceAware(false);
			DocumentBuilder parser = dbf.newDocumentBuilder();
			String source = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+URLEncoder.encode(station, "UTF-8")+"&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=4snM6oJ0micT5aKrx8VnWpXPcSNhM7IzFkDb79yPLbOeBoBOGGFhuGyJezZrYrx1H6OZY7r89U4cJsMKoXR1Gg%3D%3D&ver=1.3";
			//&_returnType=json
			System.out.println(source);
			doc = parser.parse(source);
			Element mm = doc.getDocumentElement();
			NodeList nodelist2 = doc.getElementsByTagName("item");
			System.out.println("nodelist2 : " + nodelist2);		//xml 주소값 나온다
			
			//for (int i = 0; i < nodelist2.getLength(); i++) {			//for문 돌리면 최근 10시간전 데이터까지 조회되서 나오지만 최근 데이터만 받기 위해서 for문 안돌림
				// NodeList nodelist3 = document.getElementsByTagName("item");
				// Element ms = (Element) nodelist2.item(i);	//for문용 	
			ParticulateMatterTo pmTo = new ParticulateMatterTo();
				Element ms = (Element) nodelist2.item(0);
				NodeList dataTimeNode = ms.getElementsByTagName("dataTime");
				NodeList pm10ValueNode = ms.getElementsByTagName("pm10Value");
				NodeList pm25ValueNode = ms.getElementsByTagName("pm25Value");
				NodeList khaiValueNode = ms.getElementsByTagName("khaiValue");
				NodeList khaiGradeNode = ms.getElementsByTagName("khaiGrade");

				//Element stationEl = (Element) stationNode.item(0);
				Element dataTimeEl = (Element) dataTimeNode.item(0);
				Element pm10ValueEl = (Element) pm10ValueNode.item(0);
				Element pm25ValueEl = (Element) pm25ValueNode.item(0);
				Element khaiValueEl = (Element) khaiValueNode.item(0);
				Element khaiGradeEl = (Element) khaiGradeNode.item(0);

				String dataTime = dataTimeEl.getFirstChild().getTextContent();
				String pm10Value = pm10ValueEl.getFirstChild().getTextContent();
				String pm25Value = pm25ValueEl.getFirstChild().getTextContent();
				String khaiValue = khaiValueEl.getFirstChild().getTextContent();
				String khaiGrade = khaiGradeEl.getFirstChild().getTextContent();
				
				System.out.println("=========="+station+"의 실시간 대기환경 조회 결과==========");
				System.out.println("측정일시 : " + dataTime);
				System.out.println("미세먼지(PM10) 농도 : " + pm10Value);
				System.out.println("미세먼지(PM2.5) 농도 : " + pm25Value);
				System.out.println("통합대기환경수치 : " + khaiValue);
				System.out.println("통합대기환경지수 : " + khaiGrade);
				System.out.println("=============================================\n");
				pmTo.setDataTime(dataTime);
				pmTo.setPm10Value(pm10Value);
				pmTo.setPm25Value(pm25Value);
				pmTo.setKhaiValue(khaiValue);
				pmTo.setKhaiGrade(khaiGrade);
				pmList.add(pmTo);
			//}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pmList;
	}	
	
	
/*	@RequestMapping("base/findDustStatus.do")
	public void findDustStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("findDustStatus");
		request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); 
																														 * URL
																														 
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=nRV0AEg5RGOwvwU5%2Blqcye0CUldYPkU584voPxLgOby2avVlvTZvjniEjr8PQCQbtHJYjqJvjsEyLvmXUmUQ%2Bw%3D%3D"); 
																														 * Service
																														 * Key
																														 
		 urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" +
		 URLEncoder.encode("10", "UTF-8")); 한 페이지 결과 수
		 urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" +
		 URLEncoder.encode("1", "UTF-8")); 페이지 번호
		 urlBuilder.append("&" + URLEncoder.encode("sidoName", "UTF-8") + "=" + URLEncoder.encode("경남","UTF-8"));  시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종) 
		 urlBuilder.append("&" + URLEncoder.encode("dataTerm","UTF-8") + "=" +
		 URLEncoder.encode("DAILY", "UTF-8")); 요청 데이터기간 (시간 : HOUR, 하루 : DAILY)
		 urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" +
		 URLEncoder.encode("1.3", "UTF-8")); 오퍼레이션 버전

		Document doc = null;
		List<DustStatusTO> dustStatusList = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(urlBuilder.toString());
			NodeList descNodes = doc.getElementsByTagName("item");
			for (int i = 0; i < descNodes.getLength(); i++) {
				DustStatusTO dustTO = new DustStatusTO();



				for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행

	                if(node.getNodeName().equals("stationName")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setStationName(node.getTextContent());
	                }
	                if(node.getNodeName().equals("dataTime")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setDataTime(node.getTextContent());

	                }
	                if(node.getNodeName().equals("pm10Grade")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setPm10Grade(node.getTextContent());
	                }
	                if(node.getNodeName().equals("so2Value")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setSo2Value(node.getTextContent());
	                }
	                if(node.getNodeName().equals("coValue")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setCoValue(node.getTextContent());
	                }
	                if(node.getNodeName().equals("pm25Grade")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setPm25Grade(node.getTextContent());
	                }
				}
				  dustStatusList.add(dustTO);
			}
		} catch (Exception ex) {
			throw ex;
		}

		datasetBeanMapper.beansToDataset(outData, dustStatusList, DustStatusTO.class);
	}
*/
}