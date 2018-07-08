package com.ryanair.api.searchflights.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.ryanair.api.searchflights.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class InterconnectionsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void createARegularFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
				.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
						.param("departure", "DUB")
						.param("arrival", "WRO")
						.param("departureDateTime", "2018-08-01T10:00")
						.param("arrivalDateTime", "2018-08-02T22:00")
						)
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void createASameDestinationFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
				.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
						.param("departure", "DUB")
						.param("arrival", "DUB")
						.param("departureDateTime", "2018-08-01T10:00")
						.param("arrivalDateTime", "2018-08-02T22:00")
						)
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void createAInvalidDateFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
				.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
						.param("departure", "DUB")
						.param("arrival", "WRO")
						.param("departureDateTime", "2018-08-01T10:00")
						.param("arrivalDateTime", "2018-07-02T22:00")
						)
				.andDo(print()).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void createAInvalidAirportFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
		.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
				.param("departure", "DUBBBBB")
				.param("arrival", "WRO")
				.param("departureDateTime", "2018-08-01T10:00")
				.param("arrivalDateTime", "2018-08-02T22:00")
				)
		.andDo(print()).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void createAParameterMissingFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
		.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
				.param("arrival", "WRO")
				.param("departureDateTime", "2018-08-01T10:00")
				.param("arrivalDateTime", "2018-08-02T22:00")
				)
		.andDo(print()).andExpect(status().isBadRequest());
	}
	
	@Test(expected=NestedServletException.class)
	public void createAParameterInvalidDateFormatFilter() throws UnsupportedEncodingException, Exception {
		this.mockMvc
		.perform(get("/interconnections").contentType(MediaType.APPLICATION_JSON)
				.param("departure", "DUB")
				.param("arrival", "WRO")
				.param("departureDateTime", "01/02/2018")
				.param("arrivalDateTime", "2018-08-02T22:00")
				)
		.andDo(print()).andExpect(status().isInternalServerError());
	}

}
