
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String m : movies)
        {
            double avgRating = getAverageByID(m, minimalRaters);
            if(avgRating != 0.0)
            {
                Rating rating = new Rating(m, avgRating);
                ratingsData.add(rating);
            }
        }
        return ratingsData;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> ratingData = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String m : movies)
        {
            double currRating = getAverageByID(m, minimalRaters);
            if(currRating != 0.0)
            {
                Rating rating = new Rating(m, currRating);
                ratingData.add(rating);
            }
        }
        return ratingData;
    }
                
        
    
    
        
        
}
