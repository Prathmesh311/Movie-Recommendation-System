
/**
 * Write a description of FirstRatings here.
 * 
 * @author Prathmesh bhondave 
 * @version 6/13/2020
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {


    public ArrayList<Movie> loadMovies(String fileName)
    {
        ArrayList<Movie> data = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord rec : parser)
        {
            Movie currMovie = new Movie(rec.get("id"), rec.get("title"),rec.get("year"),rec.get("genre"),
                                        rec.get("director"),rec.get("country"),rec.get("poster"),Integer.parseInt(rec.get("minutes")));
            data.add(currMovie);
           
        }
        return data;
    }
    
    public void testLoadMovies()
    {
        //ArrayList<Movie> moviesData = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> moviesData = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> moviesData = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String, Integer> directorMovies = new HashMap<String, Integer>();
        /*for(Movie s : moviesData)
        {
            System.out.println(s);
        }
        System.out.println(moviesData.size());*/
        
        int genreCount = 0;
        int Movie150Min = 0;
        for(Movie s : moviesData)
        {
            if(s.getGenres().contains("Comedy"))
            {
                genreCount++;
            }
            if(s.getMinutes() > 150)
            {
                Movie150Min++;
            }
            
        }
        
        int directedMost = 0;
        String directorName = "";
        for(Movie s : moviesData)
        {
            if(!directorMovies.containsKey(s.getDirector()))
            {
                directorMovies.put(s.getDirector(), 1);
            }
            else
            {
                directorMovies.put(s.getDirector(), directorMovies.get(s.getDirector()) + 1);
            }
        }
        for(String map : directorMovies.keySet())
        {
            int numDirected = directorMovies.get(map);
            if(numDirected > directedMost)
            {
                directorName = map;
                directedMost = numDirected;
            }
        }
            
        System.out.println("Comedy genre = " + genreCount);
        System.out.println("Movies more than 150 min = " + Movie150Min);
        System.out.println("Director With most nnumber of movies = " + directorName + " times " + directedMost);
        
    }
    
    public ArrayList<Rater> loadRaters(String fileName)
    {
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        
        ArrayList<Rater> raterData = new ArrayList<Rater>();
        ArrayList<String> raterIds = new ArrayList<String>();
        for(CSVRecord rec : parser)
        {
            String raterId = rec.get("rater_id");
            String movieId = rec.get("movie_id");
            double rating = Double.parseDouble(rec.get("rating"));
            
            
            if(!raterIds.contains(raterId))
            {
                Rater  currRater = new EfficientRater(raterId);
                currRater.addRating(movieId, rating);
                raterData.add(currRater);
            }
            else
            {
                for(int i=0; i < raterData.size(); i++)
                {
                    if(raterData.get(i).getID().equals(raterId))
                    {
                        raterData.get(i).addRating(movieId, rating);
                    }
                }
            }
            raterIds.add(raterId);
        }
                
        return raterData;
    }
    
    public void testLoadRaters()
    {
        //ArrayList<Rater> raterData = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> raterData = loadRaters("data/ratings.csv");
        
        System.out.println("Total raters = " + raterData.size());
        /*for(Rater r : raterData)
        {
            ArrayList<String> Items = new ArrayList<String>();
            System.out.println(r.getID() + " " + r.numRatings());
            ArrayList<String> itemsRated = r.getItemsRated();
            for(String item : itemsRated)
            {
                if(!Items.contains(item))
                {
                    System.out.println(item + " " + r.getRating(item));
                    Items.add(item);
                }
            }
        }*/
                
        String Id = "193";
        int maxRatings = 0;
        String maxRaterID = "";
        String movie_ID = "1798709";
        int numRatingsForMovie = 0;
        ArrayList<String> allMoviesRated = new ArrayList<String>();
        
        for(Rater r : raterData)
        {
            if(r.getID().equals(Id))
            {
                System.out.println("Rating for raterId " + Id +" are " + r.numRatings());
            }
            
            if(r.numRatings() > maxRatings)
            {
                maxRaterID = r.getID();
                maxRatings = r.numRatings();
            }
            
            if(r.hasRating(movie_ID))
            {
                numRatingsForMovie++;
            }
            
            ArrayList<String> moviesRatedByRater = r.getItemsRated();
            for(String item : moviesRatedByRater)
            {
                if(!allMoviesRated.contains(item))
                {
                    allMoviesRated.add(item);
                }
            }
                    
            
        
        }
        
        System.out.println("RaterID with maximum ratings  " + maxRaterID + " with ratings " + maxRatings);
        System.out.println("Movie with Id " + movie_ID + " has ratings " + numRatingsForMovie);
        System.out.println("Total unique movies rated are " + allMoviesRated.size());
    }
        
}
