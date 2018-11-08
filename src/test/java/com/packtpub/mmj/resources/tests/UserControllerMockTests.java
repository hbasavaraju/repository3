package com.packtpub.mmj.resources.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.packtpub.mmj.user.UsersApp;
import com.packtpub.mmj.user.domain.model.entity.User;
import com.packtpub.mmj.user.domain.service.UserService;
import com.packtpub.mmj.user.resources.UserController;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@ContextConfiguration(classes={UsersApp.class})
public class UserControllerMockTests {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;
	
	String expected = "{\"id\":\"name1\",\"name\":\"id1\",\"isModified\":false,\"address\":\"address1\",\"city\":\"city1\",\"phoneNo\":\"88797808\"}";
	
	
	// https://codebeautify.org/java-escape-unescape
	@Test
	public void testUserFindById() throws Exception {
		System.out.println("**********************************running-sample********************************");
		//assert(false);
		
		User user = new User("name1","id1","address1","city1","88797808");
		
		
		Mockito.when(userService.findById(Mockito.anyString())).thenReturn(user);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/v1/user/1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("result="+result.getResponse().getContentAsString());
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	}

}
