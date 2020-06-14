
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    private double getAverageByID(String ID,int minimalRaters)
    {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        double numOfRaters = 0.0;
        double totalRating = 0.0;
        
        for(Rater r : raters)
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
    
    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> myRatings = me.getItemsRated();
        
        double dotProduct = 0.0;
        for(String movieID : myRatings)
        {
            if(r.hasRating(movieID))
            {
                double myRate = me.getRating(movieID)-5;
                double rRate  = r.getRating(movieID)-5;
                dotProduct += myRate*rRate;
            }
            
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> similarity = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        
        for(Rater r : raters)
        {
            if(!r.getID().equals(id))
            {
                double product = dotProduct(me, r);
                if(product > 0.0)
                {
                    similarity.add(new Rating(r.getID(), product));
                }
            }
        }
        Collections.sort(similarity, Collections.reverseOrder());
        
        return similarity;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : movies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }            
                
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria)
    {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : filteredMovies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
        
       
            
        
}
