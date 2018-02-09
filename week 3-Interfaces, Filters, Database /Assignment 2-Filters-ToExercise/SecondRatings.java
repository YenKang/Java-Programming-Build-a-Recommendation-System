 
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String loadRaters){
        //create a FirsrRatings object
        FirstRatings fr = new FirstRatings();
        // call the loadMovies and loadRaters
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(loadRaters);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id,int minimalRaters){
        double average =0;
        double total=0;
        int countRaters= 0;
        for(Rater rater:myRaters){
            if(rater.hasRating(id)){
                countRaters++;
                total = total + rater.getRating(id);
            }
        }
        
        if(countRaters >= minimalRaters){
            average = total/countRaters;
        }
        return average;
        
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
        for(Movie movie:myMovies){

            //System.out.println("movie: " + movie);
            String id = movie.getID();
            //System.out.println("Get Movie ID: "+ id);
            double avg = getAverageByID(id,minimalRaters);
            //System.out.println("avg: " + avg);

            if(avg>0.0){
                Rating currRating = new Rating(id,avg);
                avgRatingList.add(currRating);
            }    
        }
        return avgRatingList;
    }
    
    public String getTitle(String id){
        String title= "";
        for(Movie movie:myMovies){
            if(movie.getID().equals(id)){
                title = movie.getTitle();
                break;
            }
        }
        if(title == ""){
            return "This id is Not Found";
        }
        
        else{
            return id;
        }
    }
    
    public String getID(String title){
        String id ="";
        for(Movie movie:myMovies){
            if(movie.getTitle().equals(title)){
                id = movie.getID();
                break;
            }
        }
        
        if(id ==""){
            return "No SUCH TITLE";
        }
        else{
            return id;
        }
        
    }
    
    
    
}
