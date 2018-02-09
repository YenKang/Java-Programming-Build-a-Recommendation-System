
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){

        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        //create ThirdRatings object
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("Number of raters: "+ tr.getRaterSize());

        MovieDatabase.initialize(moviefile);
        System.out.println("Number of movies rated: "+ MovieDatabase.size());

        ArrayList<Rating> avgRatingList = tr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatingList);
        System.out.println("avgRatingList size " + avgRatingList.size());

        for(Rating r:avgRatingList){
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue()+ " : " + Title);
        }  

    }

    public void printAverageRatingsByYear(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";

        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        // create YearAfterFilter 
        Filter yf = new YearAfterFilter(2000);
        //AllFilters af = new AllFilters();
        //af.addFilter(yf);

        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,yf);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            int Year = MovieDatabase.getYear(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Year + " " + Title);
        }
    }

    public void printAverageRatingsByGenre(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        String selecGenre = "Crime";

        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter gr = new GenreFilter(selecGenre);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,gr);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            String Genre = MovieDatabase.getGenres(r.getItem());
            String Title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + Title + " [" + Genre + "]");
        }
    }

    public void printAverageRatingsByMinutes(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        int minMinutes = 110;
        int maxMinutes = 170;
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,mr);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            int Time = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " " + "Time: "+ Time+ " "+ Title);
        }
    }

    public void printAverageRatingsByDirectors(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";

        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter dr = new DirectorsFilter(directors);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,dr);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(r.getValue()+ " " + Title);
            System.out.println("       " + Directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        int yearCount = 1980;
        String selecGenre = "Romance";

        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter yf = new YearAfterFilter(yearCount);
        Filter gf = new GenreFilter(selecGenre);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yf);
        filtersList.addFilter(gf);

        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,filtersList);
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

     public void printAverageRatingsByDirectorsAndMinutes(){
        int minimalRaters =1;
        String moviefile = "ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";

        int minMinutes = 30;
        int maxMinutes = 170;
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";


        ThirdRatings tr = new ThirdRatings(ratingsfile);
        System.out.println("read data for "+ tr.getRaterSize() + " raters");

        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+ MovieDatabase.size() + " movies");

        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        Filter dr = new DirectorsFilter(directors);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mr);
        filtersList.addFilter(dr);

        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println(list.size() + " movie " + "matched");

        for(Rating r:list){
            String matechedTitle = MovieDatabase.getTitle(r.getItem());
            String matchedDirectors = MovieDatabase.getDirector(r.getItem());
            int matchMinutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue()+ " "+ " Time: "+matchMinutes+ " " +matechedTitle);
            System.out.println("      " + matchedDirectors);
        }



    }


    
}
