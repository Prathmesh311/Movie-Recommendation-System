
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate()
    {
        ArrayList<String> moviesForRating = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(int i=0; i < 20; i++)
        {
            Random random = new Random();
            int movieID = random.nextInt(movies.size());
            if(!moviesForRating.contains(movies.get(movieID)))
            {
                moviesForRating.add(movies.get(movieID));
            }
        }
        return moviesForRating;
    }
    
    public void printRecommendationsFor(String webRaterID)
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 50, 5);
        
        if(ratings.size() == 0)
        {
            System.out.println("No matching movies found");
        }
        else
        {
            System.out.println("<Table> <tr> <th>Movie title</th>  <th>Rating value</th>  <th>Genre</th>  </tr> ");
            String body = "";
            for(Rating r : ratings)
            {
                body += "<tr> <td>" + MovieDatabase.getTitle(r.getItem()) + "</td> <td>" + 
                            r.getValue() +"</td> <td>" + MovieDatabase.getGenres(r.getItem()) + "</td> </tr>";
            }
            System.out.println(body + "</Table>");
        }
    }
        
        

}
