import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet implements ServletContextListener{
	// Server-side method that processes the query request
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final long startTime = System.currentTimeMillis();
		ServletContext ctx = request.getServletContext();
		VectorEmbedding vEmbedding = (VectorEmbedding) ctx.getAttribute("vEmbedding");
		HashMap<String, Float> map = new HashMap<>();
		String query = request.getParameter("query");
		map = vEmbedding.computeQuery(query);
		for(String word : map.keySet()){
			response.getOutputStream().println("Word :" + word + "\t\t\t\tCS Value: " + map.get(word));
        }
		
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		VectorEmbedding vEmbedding = new VectorEmbedding();
		FileReader fr;
		try {
			fr = new FileReader("glove.6B.50d.txt");
		BufferedReader br = new BufferedReader(fr);
		String read = null;
		while ((read = br.readLine()) != null)  {
            // Split space between word and float number embedding
            String[] words = read.split(" ");
            // Array of Floats which will keep as values for words
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }
            vEmbedding.put(words[0],values);
        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		servletContextEvent.getServletContext().setAttribute("vEmbedding",vEmbedding);
	}
}