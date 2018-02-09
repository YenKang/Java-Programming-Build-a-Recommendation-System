/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * extrance link:http://www.dukelearntoprogram.com/capstone/recommender.php?id=m10ZYHs7JxgOdD
 *
 */

import java.util.*;

public class RecommendationRunner implements Recommender {

	public ArrayList<String> getItemsToRate(){
		ArrayList<String> moviesToRate = new ArrayList<String>();

		String [] movies ={"1126590","1179031","1198156","1216491",
							"1361318","1408253","1430612","1441953",
							"1490017","1528100","1571249","1600196"};
		for(int i=0;i<12;i++){
			moviesToRate.add(movies[i]);
		}
		return moviesToRate;
	}

	public void printRecommendationsFor(String webRaterID){
		FourthRatings fr = new FourthRatings();
		int numSimilarRaters = 5;
		int minimalRaters = 3 ;

		ArrayList<Rating> recList = fr.getSimilarRatings(webRaterID,numSimilarRaters,minimalRaters);
		
		if(recList.size()==0){
			printError();
		}

		else{
			printUpperPart();
             int i=0;
             for(Rating r: recList){
                 i++;
                 if((i+1)%2 == 0){
                 	System.out.println("<tr class=\"even_rows\"><td>" + i + "</td>");
                 }
                 else{
                 	System.out.println("<tr class=\"odd_rows\"><td>" + i + "</td>");
                 }

                 String URL = MovieDatabase.getPoster(r.getItem());
                 String title = MovieDatabase.getTitle(r.getItem());
                 String director = MovieDatabase.getDirector(r.getItem());
                 String country = MovieDatabase.getCountry(r.getItem());
                 int year = MovieDatabase.getYear(r.getItem());
                 String genre = MovieDatabase.getGenres(r.getItem());
                 int minutes = MovieDatabase.getMinutes(r.getItem());

                 System.out.println("<td><table><tr><td class = \"pic\">");

                 if(URL.length()>3){
                 	System.out.println("<img src = \""+URL+"\" target=_blank></td>");
                 }
                  
                 System.out.println("<td><h3>"+ title+"</h3>");
                 System.out.println("<b>by "+ genre+"</b><br>");
                 System.out.println(year+"<br>");
                 System.out.println(country+"<br>");
                 System.out.println(minutes+" minutes</td></tr></table></td></tr>");
                 if(i>12) break;
             }
             printLowerPart();

		}
	}

	private void printError(){
		System.out.println("This is system error, please try again!");
	}

  	private void printUpperPart(){
        System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Recommended Movies:</h2></div><table class=\"outside_table\"><tr  class=\"table-header\"><th>&nbsp</th><th class=\"movie_title\">Title</th></tr>");
     }
     
    private void printCSS(){
         System.out.println("<style>* {margin: 0;padding: 0;}img{height: 100px;margin-right:10px;}#header{background-color: #F49F58;margin-top: 0;height: 100px;}h2{padding-left: 15px;padding-top: 40px;color: #FFFFFF;}h3{}body{margin-top: 0;font-family: 'Arial'}th{text-align: left;font-family: 'Arial', sans-serif;padding-top:15px;padding-bottom: 7px;}td{padding-top: 10px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;}tr{padding-bottom: 10px;}.table-header{background-color: #FFB97F;}.odd_rows{background-color: #FFE4CC;}.even_rows{background-color: #FFFFFF;}.outside_table{width: 100%;border-collapse: collapse;}.movie_title{width = 40%;}</style>");
     }
     
    private void printLowerPart(){
        System.out.println("</table>");
    }
}