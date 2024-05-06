package rpc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;

/**
 * Servlet implementation class SearchItem
 */
@WebServlet("/search")
public class SearchItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//JSONArray array = new JSONArray();
		//try {
		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		// Term can be empty or null.
		String term = request.getParameter("term");
		
		DBConnection conn = DBConnectionFactory.getConnection();
		List<Item> items = conn.searchItems(lat, lon, term);
		conn.close();
		
		Set<String> favorite = conn.getFavoriteItemIds(userId);
		conn.close();
		
		List<JSONObject> list = new ArrayList<>();
		try {
		for (Item item : items) {
		// Add a thin version of item object
		JSONObject obj = item.toJSONObject();
		
		// Check if this is a favorite one.
		// This field is required by frontend to correctly display favorite items.
		obj.put("favorite", favorite.contains(item.getItemId()));
		
		list.add(obj);
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		JSONArray array = new JSONArray(list);
		RpcHelper.writeJsonArray(response, array);

		
		//TicketMasterAPI tmAPI = new TicketMasterAPI();
		//List<Item> items = tmAPI.search(lat, lon, keyword);
		//for (Item item : items) {
		//JSONObject obj = item.toJSONObject();
		//array.put(obj);
		//}
		//} catch (Exception e) {
		//e.printStackTrace();
		//}
		//RpcHelper.writeJsonArray(response, array);
		
		
//		JSONArray array = new JSONArray();
//		try {
//		array.put(new JSONObject().put("username", "abcd"));
//		array.put(new JSONObject().put("username", "1234"));
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		RpcHelper.writeJsonArray(response, array);
		
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		JSONArray array = new JSONArray();
//		try {
//		array.put(new JSONObject().put("username", "abcd"));
//		array.put(new JSONObject().put("username", "1234"));
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		out.print(array);
//		out.close();
		
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		String username = "";
//		if (request.getParameter("username") != null) {
//		username = request.getParameter("username");
//		}
//		JSONObject obj = new JSONObject();
//		try {
//		obj.put("username", username);
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		out.print(obj);
//		out.close();
		
//		PrintWriter out = response.getWriter();
//		if (request.getParameter("username") != null) {
//		String username = request.getParameter("username");
//		out.print("Hello " + username);
//		}
//		out.close();
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
