
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings ()
    {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        System.out.println("number of Movies = " + sr.getMovieSize());
        System.out.println("Number of raters = " + sr.getRaterSize());
        
        ArrayList<Rating> ratingsData = sr.getAverageRatings(2);
        Collections.sort(ratingsData);
        
        for(Rating r : ratingsData)
        {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie()
    {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        
        //String movieID = sr.getID("The Godfather");
        String movie = "Vacation";
        int minimumRaters = 1;
        
        ArrayList<Rating> avgRating = sr.getAverageRatings(50);
        System.out.println("Movies with higher rating = " + avgRating.size());
        
        String movieID = sr.getID(movie);
        
        for(Rating r : avgRating)
        {
            if(r.getItem().equals(movieID))
            {
                System.out.println("Average rating of movie " + sr.getTitle(movieID) + " is " + r.getValue());
            }
        }
    }
}
