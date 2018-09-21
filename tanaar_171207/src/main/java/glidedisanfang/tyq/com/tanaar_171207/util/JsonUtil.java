package glidedisanfang.tyq.com.tanaar_171207.util;


import com.google.gson.Gson;

public class JsonUtil<T> {

	public Object convertJsonToObject(String value,Class<T> object){
		Object resultObject = new Object();
		Gson gson = new Gson();
		resultObject = gson.fromJson(value, object);
		return resultObject;
	}
	
	public static Object convertJsonToObject(String value){
		Object resultObject = new Object();
		Gson gson = new Gson();
		resultObject = gson.fromJson(value, Object.class);
		return resultObject;
	}
	
	public static String convertObjectToJson(Object obj){
		String result = "";
		Gson gson = new Gson();
		result = gson.toJson(obj);
		return result;
	}
}
