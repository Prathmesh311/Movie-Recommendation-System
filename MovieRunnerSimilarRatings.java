
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");


        System.out.println("Number of Movies = "  + MovieDatabase.size());
        System.out.println("Number of raters = " + RaterDatabase.size());
        
        ArrayList<Rating> ratingsData = fr.getAverageRatings(35);
        Collections.sort(ratingsData);
        
        for(Rating r : ratingsData)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(ratingsData.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        GenreFilter gf = new GenreFilter("Drama");
        YearAfterFilter yf = new YearAfterFilter(1990);
        AllFilters AF = new AllFilters();
        AF.addFilter(gf);
        AF.addFilter(yf);
        
        ArrayList<Rating> movies = fr.getAverageRatingsByFilter(8,AF);
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " 
                                + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println(movies.size());
    }
    
    public void printSimilarRatings ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        ArrayList<Rating> ratings = fr.getSimilarRatings("71",20,5);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        System.out.println("Total recommendations: " + ratings.size());
    }
    
    public void printSimilarRatingsByGenre ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        GenreFilter gf = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964",20,5,gf);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        System.out.println("Total recommendations: " + ratings.size());
    }
    
    public void printSimilarRatingsByDirector ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120",10,2,df);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        System.out.println("Total recommendations: " + ratings.size());
    }
    
    public void printSimilarRatingsByGenreAndMinutes ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters AF = new AllFilters();
        GenreFilter gf = new GenreFilter("Drama");
        AF.addFilter(gf);
        MinutesFilter mf = new MinutesFilter(80,160);
        AF.addFilter(mf);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168",10,3,AF);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        System.out.println("Total recommendations: " + ratings.size());
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes ()
    {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters AF = new AllFilters();
        YearAfterFilter yf = new YearAfterFilter(1975);
        AF.addFilter(yf);
        MinutesFilter mf = new MinutesFilter(70,200);
        AF.addFilter(mf);
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314",10,5,AF);
        for(Rating r : ratings)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        System.out.println("Total recommendations: " + ratings.size());
    }
}
