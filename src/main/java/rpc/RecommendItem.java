package rpc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import algorithm.GeoRecommendation;
import entity.Item;

/**
 * Servlet implementation class RecommendItem
 */
@WebServlet("/recommendation")
public class RecommendItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		GeoRecommendation recommendation = new GeoRecommendation();
		List<Item> items = recommendation.recommendItems(userId, lat, lon);
		JSONArray result = new JSONArray();
		try {
		for (Item item : items) {
		result.put(item.toJSONObject());
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, result);
				
		
		
		//JSONArray array = new JSONArray();
		//try {
		//array.put(new JSONObject().put("username", "abcd"));
		//array.put(new JSONObject().put("username", "1234"));
		//} catch (JSONException e) {
		//e.printStackTrace();
		//}
		//RpcHelper.writeJsonArray(response, array);
				
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		JSONArray array = new JSONArray();
//		try {
//		array.put(new JSONObject().put("name", "abcd").put("address", "sanfrancisco").put("time", "01/01/2017"));
//		array.put(new JSONObject().put("name", "1234").put("address", "sanjose").put("time", "01/02/2017"));
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		out.print(array);
//		out.close();
//		
		
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
