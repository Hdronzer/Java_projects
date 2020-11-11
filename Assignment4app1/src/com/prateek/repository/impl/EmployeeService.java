package com.prateek.repository.impl;

import static com.prateek.utils.Constants.REST_URL;
import static com.prateek.utils.Constants.TYPE_JSON;
import static com.prateek.utils.Constants.ERROR_EMP_FETCH;
import static com.prateek.utils.Constants.ERROR_EMP_CREATE;
import static com.prateek.utils.Constants.ERROR_EMP_UPDATE;
import static com.prateek.utils.Constants.ERROR_JSON_PARSE;
import static com.prateek.utils.Constants.ERROR_OBJECT_PARSE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prateek.model.Employee;
import com.prateek.repository.IEmployeeRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * employee repository class to make network calls and consume rest api for employee details
 */
@Repository
public class EmployeeService implements IEmployeeRepository {
	private OkHttpClient client;
	private MediaType JSON;
	private String url;
	
	private static final String TAG = "EmployeeService: ";
	private final String SUCCESS = "Success";
	
	public EmployeeService() {
		client = new OkHttpClient();
		JSON = MediaType.parse(TYPE_JSON);
		url = String.join("/",REST_URL,"employee");
	}
	
	@Override
	public List<Employee> getEmployeeDetails() {
		List<Employee> list = null;
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Accept", TYPE_JSON)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			String networkResp = response.body().string();
			if (!networkResp.isEmpty()) {
				list = convertJsonToList(networkResp);
			}
		} catch (IOException e) {
			System.err.println(TAG + ERROR_EMP_FETCH);
			System.err.println(TAG + e);
		}
		return list;
	}
	
	private List<Employee> convertJsonToList(String json) {
		List<Employee> list = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.EAGER_DESERIALIZER_FETCH);
		
		try {
			list = new ArrayList<>(Arrays.asList(mapper.readValue(json, Employee[].class)));
		} catch (JsonProcessingException e) {
			System.err.println(TAG + ERROR_OBJECT_PARSE);
			System.err.println(TAG + e);
		}
		return list;
	}

	@Override
	public boolean updateEmployeeDetails(Employee emp) {
		boolean result = false;
		RequestBody body;
		String json = convertPojoToJson(emp);
		body = RequestBody.create(json,JSON);
		
		Request request = new Request.Builder()
				.url(String.join("/",url,"update") )
				.post(body)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			if (response.code() == 200) {
				result = true;
			}
		} catch (IOException e) {
			System.err.println(TAG + ERROR_EMP_UPDATE);
			System.err.println(TAG + e);
		}
		return result;
	}
	
	@Override
	public boolean deleteEmployeeDetails(int id) {
		boolean result = false;
		
		Request request = new Request.Builder()
				.url(String.join("/",url,"delete",""+id))
				.delete()
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			if (response.code() == 200) {
				result = true;
			}
		} catch (IOException e) {
			System.err.println(TAG + ERROR_EMP_UPDATE);
			System.err.println(TAG + e);
		}
		return result;
	}
	
	private String convertPojoToJson(Object obj) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			System.err.println(TAG + ERROR_JSON_PARSE);
			System.err.println(TAG + e);
		}
		return json;
	}

	@Override
	public boolean insertEmployeeDetails(Employee emp) {
		boolean result = false;
		RequestBody body;
		String json = convertPojoToJson(emp);
		body = RequestBody.create(json,JSON);
		
		Request request = new Request.Builder()
				.url(String.join("/",url,"create") )
				.post(body)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			if (response.code() == 200) {
				result = true;
			}
		} catch (IOException e) {
			System.err.println(TAG + ERROR_EMP_CREATE);
			System.err.println(TAG + e);
		}
		return result;
	}

}
