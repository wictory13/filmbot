package utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import structures.Film;
import structures.Field;
import utils.FilmUtils;

public class FilmUtilsTest {
	private List<Film> filmList = new ArrayList<Film>();
	private Film film1 = FilmUtils.getFilm("13", "Криминальное чтиво", "США", "1994", "триллер, комедия, криминал");
	private Film film2 = FilmUtils.getFilm("12", "Крестный отец", "США", "1972", "драма, криминал");
	private Map<String, List<Film>> genreMap = new HashMap<String, List<Film>>();
	private Map<String, List<Film>> yearMap = new HashMap<String, List<Film>>();
	private Map<String, List<Film>> countryMap = new HashMap<String, List<Film>>();
	private List<String[]> stringList = new ArrayList<String[]>();

	@Before
	public void createFilmList() {
		filmList.add(film1);
		filmList.add(film2);
	}

	@Before
	public void getGenreMap() {
		List<Film> dramaList = new ArrayList<Film>();
		dramaList.add(film2);
		List<Film> comedyList = new ArrayList<Film>();
		comedyList.add(film1);
		List<Film> thrillerList = new ArrayList<Film>();
		thrillerList.add(film1);
		List<Film> criminalList = new ArrayList<Film>();
		criminalList.add(film1);
		criminalList.add(film2);
		genreMap.put("драма", dramaList);
		genreMap.put("комедия", comedyList);
		genreMap.put("триллер", thrillerList);
		genreMap.put("криминал", criminalList);
	}

	@Before
	public void getYearMap() {
		List<Film> list1972 = new ArrayList<Film>();
		list1972.add(film2);
		List<Film> list1994 = new ArrayList<Film>();
		list1994.add(film1);
		yearMap.put("1972", list1972);
		yearMap.put("1994", list1994);
	}

	@Before
	public void getCountryMap() {
		List<Film> listUSA = new ArrayList<Film>();
		listUSA.add(film1);
		listUSA.add(film2);
		countryMap.put("США", listUSA);
	}

	@Before
	public void createStringList() {
		stringList.add(new String[] { "13", "Криминальное чтиво", "США", "1994", "триллер, комедия, криминал" });
		stringList.add(new String[] { "12", "Крестный отец", "США", "1972", "драма, криминал" });
	}

	@Test
	public void testGetFilmMapsByField() {
		Map<Field, Map<String, List<Film>>> filmsData = FilmUtils.getFilmMapsByField(filmList);
		assertEquals(filmsData.get(Field.GENRE), genreMap);
		assertEquals(filmsData.get(Field.YEAR), yearMap);
		assertEquals(filmsData.get(Field.COUNTRY), countryMap);
	}

	@Test
	public void testStringListToFilmList() {
		assertEquals(filmList, FilmUtils.stringListToFilmList(stringList));
	}

	@Test
	public void testFilmListToStringList() {
		List<String[]> testList = FilmUtils.filmListToStringList(filmList);
		for (int i = 0; i < testList.get(0).length; i++)
			assertEquals(stringList.get(0)[i], testList.get(0)[i]);
		for (int i = 0; i < testList.get(1).length; i++)
			assertEquals(stringList.get(1)[i], testList.get(1)[i]);
	}

	@Test
	public void testCreateMap() {
		assertEquals(countryMap, FilmUtils.createMap(filmList, Field.COUNTRY));
		assertEquals(genreMap, FilmUtils.createMap(filmList, Field.GENRE));
		assertEquals(yearMap, FilmUtils.createMap(filmList, Field.YEAR));
	}
}