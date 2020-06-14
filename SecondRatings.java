
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize()
    {
        return myMovies.size();
    }
    
    public int getRaterSize()
    {
        return myRaters.size();
    }
    
    
    private double getAverageByID(String ID,int minimalRaters)
    {
        double numOfRaters = 0.0;
        double totalRating = 0.0;
        
        for(Rater r : myRaters)
        {
            if(r.getItemsRated().contains(ID))
            {
                totalRating += r.getRating(ID);
                numOfRaters++;
            }
        }
        
        if(numOfRaters >= minimalRaters)
        {
            double Avg = totalRating/numOfRaters;
            return Avg;
        }
        
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> ratingsData =new ArrayList<Rating>();
        
        for(Movie m : myMovies)
        {
            double avgRating = getAverageByID(m.getID(), minimalRaters);
            if(avgRating != 0.0)
            {
                Rating rating = new Rating(m.getID(), avgRating);
                ratingsData.add(rating);
            }
        }
        return ratingsData;
    }
    
    public String getTitle(String ID)
    {
        for(Movie m : myMovies)
        {
            if(m.getID().equals(ID))
            {
                return m.getTitle();
            }
        }
        return "String indicating ID was not found";
    }
    
    public String getID(String title)
    {
        for(Movie m : myMovies)
        {
            if(m.getTitle().equals(title))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
        
        
        
}
