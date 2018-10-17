package com.dev.monitor;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dev.monitor.model.Services;
import com.dev.monitor.repository.MonitorServiceRepository;
import com.dev.monitor.service.MonitorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MonitorServiceController.class, secure = false)
public class MonitorServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MonitorService service;

	@MockBean
	private MonitorServiceRepository repository;

	List<Services> mockService = Arrays
			.asList(new Services(1, "Service1", "localhost", 8089, 15, "9:00", "10:00", "1000"));
	String exampleCourseJson = "[{\"id\":1,\"name\":\"Service1\",\"host\":\"localhost\",\"port\":8089,\"frequency\":15,\"startTime\":\"9:00\",\"endTime\":\"10:00\",\"graceTime\":\"1000\"}]";
	String exampleCourseJsonPost = "{\r\n" + "        \"id\": 2,\r\n" + "        \"name\": \"Service2\",\r\n"
			+ "        \"host\": \"localhost\",\r\n" + "        \"port\": 8085,\r\n" + "        \"frequency\": 15,\r\n"
			+ "        \"startTime\": \"9:00\",\r\n" + "        \"endTime\": \"10:40\",\r\n"
			+ "        \"graceTime\": \"1000\"\r\n" + "	\r\n" + "}";

	@Test
	public void retrieveAllServices() throws Exception {
		Mockito.when(service.getAllServices()).thenReturn(mockService);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/services/all")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		JSONArray jsonArr = new JSONArray(exampleCourseJson);
		JSONAssert.assertEquals(result.getResponse().getContentAsString(), jsonArr, false);

	}

	@Test
	public void createService() throws Exception {
		Services services = new Services(2, "Service2", "localhost", 8085, 25, "10:00", "10:00", "1000");

		Mockito.when(service.persist(services)).thenReturn(mockService);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rest/services/load")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJsonPost)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());

	}

}
