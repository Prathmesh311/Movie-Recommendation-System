
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors)
    {
        myDirectors = directors;
    }
    
    public boolean satisfies(String id)
    {
        ArrayList<String> directorList = new ArrayList(Arrays.asList(myDirectors.split(",")));
        for(String s : directorList)
        {
            if(MovieDatabase.getDirector(id).contains(s))
            {
                return true;
            }
        }
        return false;
    }
    
}
