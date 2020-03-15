import static net.grinder.script.Grinder.grinder
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*
import net.grinder.plugin.http.HTTPRequest
import net.grinder.plugin.http.HTTPPluginControl
import net.grinder.script.GTest
import net.grinder.script.Grinder
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import static net.grinder.util.GrinderUtils.* // You can use this if you're using nGrinder after 3.2.3
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import java.util.Date
import java.util.List
import java.util.ArrayList
import java.util.LinkedHashMap

import HTTPClient.Cookie
import HTTPClient.CookieModule
import HTTPClient.HTTPResponse
import HTTPClient.NVPair

import groovy.json.JsonOutput 

/**
 * A simple example using the HTTP plugin that shows the retrieval of a
 * single page via HTTP. 
 * 
 * This script is automatically generated by ngrinder.
 * 
 * @author LaVi
 * 
 * nGrinder POST 예제입니다. 대량으로 저장된 csv파일을 불러와서 JSON으로 POST 해주는 역할을 합니다. 헤더에 JSON설정필수이고 백엔드도 컨슘설정을 해주어야합니다
 */

 
 
@RunWith(GrinderRunner)
class TestRunner {

	public static def csvFile = new File("./resources/user.csv")
	public static int index=0
	public static int max
	public static testData = []

	public static GTest test
	public static HTTPRequest request
	public static NVPair[] headers = []
	public static NVPair[] params = []
	public static Cookie[] cookies = []

	@BeforeProcess
	public static void beforeProcess() {
	
		csvFile.eachLine{ line ->
			def parts = line.split(",")
			def tmpMap = [:]
			tmpMap.putAt("nickname",parts[0])
			tmpMap.putAt("password",parts[1])
			testData.add(tmpMap)
		}
		
		max = testData.size()-1
	
		HTTPPluginControl.getConnectionDefaults().timeout = 6000
		test = new GTest(1, "127.0.0.1")
		request = new HTTPRequest()
		grinder.logger.info("before process.");
	}

	@BeforeThread 
	public void beforeThread() {
		test.record(this, "test")
		grinder.statistics.delayReports=true;
		grinder.logger.info("before thread.");
	}
	
	@Before
	public void before() {
		
		
		request.setHeaders(headers)
		cookies.each { CookieModule.addCookie(it, HTTPPluginControl.getThreadHTTPClientContext()) }
		grinder.logger.info("before thread. init headers and cookies");
	}

	@Test
	public void test(){
		HTTPResponse result = request.GET("http://127.0.0.1:8080/test", params)
		
		if (result.statusCode == 301 || result.statusCode == 302) {
			grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", result.statusCode); 
		} else {
			assertThat(result.statusCode, is(200));
		}
	}
	
	
	/*
	@Test
	public void createTest(){
		def reqBody = JsonOutput.toJson(testData[index])
		index++
		request.setHeaders(new NVPair("Content-Type", "application/json"))
		HTTPResponse result = request.POST("http://127.0.0.1:8080/test/create", reqBody.getBytes())
		
		
		if (result.statusCode == 301 || result.statusCode == 302) {
			grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", result.statusCode); 
		} else {
			assertThat(result.statusCode, is(200));
		}
	}*/
	
	
	
/*
	@After
	@Test
	public void readTest(){
		HTTPResponse result = request.POST("http://127.0.0.1:8080/test/read/"+index, params)
		index++
		if (result.statusCode == 301 || result.statusCode == 302) {
			grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", result.statusCode); 
		} else {
			assertThat(result.statusCode, is(200));
		}
	}*/
	
}