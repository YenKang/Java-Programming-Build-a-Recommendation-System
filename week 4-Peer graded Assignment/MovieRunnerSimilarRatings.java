import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;


/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {

	public void printAverageRatings(){

        int minimalRaters =35;
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";

        //String moviefile = "ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
       
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);


        System.out.println("Number of raters: "+ RaterDatabase.size());

     
        System.out.println("Number of movies rated: "+ MovieDatabase.size());

        ArrayList<Rating> avgRatingList = fr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatingList);
        System.out.println("avgRatingList size " + avgRatingList.size());

        for(Rating r:avgRatingList){
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue()+ " : " + Title);
        }  

    }

    public void printAverageRatingsByYearAfterAndGenre(){
        int minimalRaters =8;
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        int yearCount = 1990;
        String selecGenre = "Drama";

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of raters: "+ RaterDatabase.size());
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter yf = new YearAfterFilter(yearCount);
        Filter gf = new GenreFilter(selecGenre);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yf);
        filtersList.addFilter(gf);

        ArrayList<Rating> list = fr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println(list.size() + " movie " + "matched");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());
            int Year = MovieDatabase.getYear(r.getItem());

            System.out.println(r.getValue()+ " " + Year +" "+ Title );
            System.out.println("       "+ Genre);
        }

    }

    public void printSimiliarRatings(){
    	int minimalRaters =5;
    	String id = "71";
    	int numSimilarRaters=20;

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        ArrayList<Rating> recommendations= fr.getSimilarRatings(id,numSimilarRaters,minimalRaters);
        //Collections.sort(recommendations);
        System.out.println(recommendations.size() + " movie " + "matched");
        System.out.println("movieSimRatings: " + recommendations);

        for(Rating rating:recommendations){
        	String movieTitle = MovieDatabase.getTitle((rating.getItem()));
        	System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printSimiliarRatingsByGenre(){
    	int minimalRaters =5;
    	String id = "964";
    	int numSimilarRaters=20;
    	String selecGenre = "Mystery";

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter gr = new GenreFilter(selecGenre);
        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,gr);
        System.out.println(recommendations.size() + " movie " + "matched");
        //System.out.println("movieSimRatings: " + recommendations);

        for(Rating rating:recommendations){
        	String movieTitle = MovieDatabase.getTitle((rating.getItem()));
        	System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printSimiliarRatingsByDirector(){
    	int minimalRaters =2;
    	String id = "120";
    	int numSimilarRaters=10;
    	String inputDirectors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

    	String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter dr = new DirectorsFilter(inputDirectors);
        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,dr);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
        	String movieTitle = MovieDatabase.getTitle((rating.getItem()));
        	System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printSimiliarRatingsByGenreAndMinutes(){
    	int minimalRaters =3;
    	String id = "168";
    	int numSimilarRaters=10;
    	int minMinutes = 80;
        int maxMinutes = 160;
    	String selecGenre = "Romance";

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

   		Filter gr = new GenreFilter(selecGenre);        
        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(gr);

        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,filtersList);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
        	String movieTitle = MovieDatabase.getTitle((rating.getItem()));
        	System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }

    public void printAverageRatingsByYearAfterAndMinutes(){
    	int minimalRaters =5;
    	String id = "314";
    	int numSimilarRaters=10;
    	int minMinutes = 70;
        int maxMinutes = 200;
        int yearCount = 1975;

        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        Filter yf = new YearAfterFilter(yearCount);
        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(yf);

        ArrayList<Rating> recommendations = fr.getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,filtersList);
        System.out.println(recommendations.size() + " movie " + "matched");

        for(Rating rating:recommendations){
        	String movieTitle = MovieDatabase.getTitle((rating.getItem()));
        	System.out.println(movieTitle+ " : " + rating.getValue());
        }
    }



    

    public void printAverageRatingsByYearAfterAndGenreAndMinutes(){
        
        int minimalRaters =5;
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";

        int minMinutes = 120;
        int maxMinutes = 180;
        int yearCount = 2004;
        String selecGenre = "Romance";

        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize(ratingsfile);
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of raters: "+ RaterDatabase.size());
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter yf = new YearAfterFilter(yearCount);
        Filter gf = new GenreFilter(selecGenre);
        Filter mf = new MinutesFilter(minMinutes,maxMinutes);

        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yf);
        filtersList.addFilter(gf);
        filtersList.addFilter(mf);

        ArrayList<Rating> list = fr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println(list.size() + " movie " + "matched");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());

            int Year = MovieDatabase.getYear(r.getItem());
            int Time = MovieDatabase.getMinutes(r.getItem());
            //System.out.println(r.getValue()+ " " + Year + " Time: "+ Time+ " "+ Title );
            //System.out.println("       "+ Genre);
            System.out.println(r);
            

        }   

    }    


}
