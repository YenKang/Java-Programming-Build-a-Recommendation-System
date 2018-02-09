import java.util.*;

public  class DirectorsFilter implements Filter{
	private String myDirectors;

	public DirectorsFilter(String directors){
		myDirectors = directors;
	}

	@Override
	public boolean satisfies(String id){
		// String curDirectors = MovieDatabase.getDirector(id);
		// return myDirectors.contains(curDirectors);

		ArrayList<String> dirList= new ArrayList(Arrays.asList(myDirectors.split(",")));
		for(String dir:dirList){
			if(MovieDatabase.getDirector(id).contains(dir)){
				return true;
			}
		}
		return false;
	}
}