
/**
 * Write a description of class EfficientRater here.
 * 
 * @author (Bryan) 
 * @version (2018-01-31)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    //private ArrayList<Rating> myRatings;
    private HashMap<String,Rating> myRatings;


    public EfficientRater(String id) {
        myID = id;
        //myRatings = new ArrayList<Rating>();
        myRatings =  new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        //myRatings.add(new Rating(item,rating));
        myRatings.put(item,(new Rating(item,rating)));
    }

    public boolean hasRating(String item) {
        /* old method
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
        */

        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }
 
    public double getRating(String item) {
        /*
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1; */

        if(hasRating(item)){
            return myRatings.get(item).getValue();
        }
        else{
            return -1;
        }
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();

        /*
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        */

        for(String movieID:myRatings.keySet()){
            list.add(movieID);
        }
        
        return list;
    }
}
