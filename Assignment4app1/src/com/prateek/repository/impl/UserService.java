package com.prateek.repository.impl;

import static com.prateek.utils.Constants.TYPE_JSON;
import static com.prateek.utils.Constants.REST_URL;
import static com.prateek.utils.Constants.ERROR_JSON_PARSE;
import static com.prateek.utils.Constants.ERROR_OBJECT_PARSE;
import static com.prateek.utils.Constants.ERROR_USER_FETCH;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prateek.model.User;
import com.prateek.repository.IUserRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * user repository class to make network calls and consume rest api for user
 */
@Repository
public class UserService implements IUserRepository {
	private OkHttpClient client;
	private MediaType JSON;
	
	private static final String TAG = "UserService: ";
	
	public UserService() {
		client = new OkHttpClient();
		JSON = MediaType.parse(TYPE_JSON);
	}
	@Override
	public User getUser(User user) {
		User resUser = null;
		RequestBody body;
		String json = convertPojoToJson(user);
		body = RequestBody.create(json,JSON);
		
		Request request = new Request.Builder()
				.url(String.join("/",REST_URL,"user"))
				.addHeader("Accept", TYPE_JSON)
				.post(body)
				.build();
		
		try (Response response = client.newCall(request).execute()) {
			
			String networkResp = response.body().string();
			if (!networkResp.isEmpty()) {
				resUser = convertJsonToPojo(networkResp);
			}
		} catch (IOException e) {
			System.err.println(TAG + ERROR_USER_FETCH);
			System.err.println(TAG + e);
		}
		return resUser;
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
	
	private User convertJsonToPojo(String json) {
		User user = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.EAGER_DESERIALIZER_FETCH);
		
		try {
			 user = mapper.readValue(json, User.class);
		} catch (JsonProcessingException e) {
			System.err.println(TAG + ERROR_OBJECT_PARSE);
			System.err.println(TAG + e);
		}
		return user;
	}

}
