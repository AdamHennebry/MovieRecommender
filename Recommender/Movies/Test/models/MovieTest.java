package models;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {


	@Test
	public void testIds(){
		Set<Long> ids = new HashSet<>();
		for (Movie movie:movies)
		{
			ids.add(movie.getMovieID());
		}
		assertEquals (movies.size(), ids.size());
	}

	


}
