import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	HashMap<String, Float> map = new HashMap<>();
	
	// Server-side method that processes the query request
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final long startTime = System.currentTimeMillis();

		String query = request.getParameter("query");
		map = GloVeReader.queryInput(query);
		for(String word : map.keySet()){
			response.getOutputStream().println("Word :" + word + "	CS Value: " + map.get(word));
        }
		
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}
}