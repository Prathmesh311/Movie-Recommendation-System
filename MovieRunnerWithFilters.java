
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");


        System.out.println("Number of Movies = "  + MovieDatabase.size());
        System.out.println("Number of raters = " + tr.getRaterSize());
        
        ArrayList<Rating> ratingsData = tr.getAverageRatings(35);
        Collections.sort(ratingsData);
        
        for(Rating r : ratingsData)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(ratingsData.size());
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
    
    public void printAverageRatingsByYear()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        YearAfterFilter f = new YearAfterFilter(2000);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(20,f);
        
        System.out.println("Number of Movies = "  + movies.size());
        System.out.println("Number of raters = " + tr.getRaterSize());
        
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(movies.size());

    }
        
        
    public void printAverageRatingsByGenre()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(20,gf);
        
        System.out.println("Number of Movies = "  + movies.size());
        System.out.println("Number of raters = " + tr.getRaterSize());
        
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println(movies.size());
    }
    
    public void printAverageRatingsByMinutes ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        MinutesFilter mf = new MinutesFilter(105,135);
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(5,mf);
        
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println(movies.size());
    }
    
    public void printAverageRatingsByDirectors ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(4,df);
        
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + " " + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println(movies.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        GenreFilter gf = new GenreFilter("Drama");
        YearAfterFilter yf = new YearAfterFilter(1990);
        AllFilters AF = new AllFilters();
        AF.addFilter(gf);
        AF.addFilter(yf);
        
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(8,AF);
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " 
                                + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println(movies.size());
    }
    
    public void printAverageRatingsByDirectorsAndMinutes ()
    {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        Filter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter mf = new MinutesFilter(90,180);
        AllFilters AF = new AllFilters();
        AF.addFilter(df);
        AF.addFilter(mf);
        
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(3,AF);
        Collections.sort(movies);
        
        for(Rating r : movies)
        {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " 
                                + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println(movies.size());
    }
        
}
