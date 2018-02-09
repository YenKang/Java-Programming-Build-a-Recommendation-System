
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        int minimalRaters =50;
        //create secondRatings object
        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
   
        SecondRatings sr = new SecondRatings(moviesFile,ratingsFile);
        // print the number of movies and the number of raters
        System.out.println("Number of rated movie: "+ sr.getMovieSize());
        System.out.println("Number of raters: "+ sr.getRaterSize());
        

        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatingList);

        for(Rating r:avgRatingList){
            System.out.println(r.getValue()+ " : " + sr.getTitle(r.getItem()));
        }  

        System.out.println("avgRatingList size " + avgRatingList.size());
    }

    public void getAverageRatingOneMovie(){
        int minimalRaters=20;

        // String title = "The Maze Runner";
        String title = "Vacation";
        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        // create a SecondRatings object
        SecondRatings sr = new SecondRatings(moviesFile,ratingsFile);

        String id = sr.getID(title);
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        // System.out.println("avgRatingList: "+ avgRatingList);
        
        for(Rating rating:avgRatingList){
            
            if(rating.getItem().equals(id)){
                System.out.println("Average rating for '" + title + "' is: " + rating.getValue());
            }

        }


        System.out.println("There are " + avgRatingList.size() + " movies that has  more than "+ minimalRaters + " ratings");
        

    }
    
}
