package rpc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public class RpcHelper {
	

	// Parses a JSONObject from http request.
	public static JSONObject readJsonObject(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
		sb.append(line);
		}
		reader.close();
		return new JSONObject(sb.toString());
		} catch (Exception e) {
		e.printStackTrace();
		}
		return null;
		}
	
	

	// Writes a JSONObject to http response.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) {
		
		try {
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(obj);
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}
	// Writes a JSONArray to http response.
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) {
	
		try {
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			PrintWriter out = response.getWriter();
			out.print(array);
			out.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
	
	}
	
	
}
