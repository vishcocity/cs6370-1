
/*****************************************************************************************
 * @file  MovieDB.java
 *
 * @author   John Miller
 */

import static java.lang.System.out;

/*****************************************************************************************
 * The MovieDB class makes a Movie Database.  It serves as a template for making other
 * databases.  See "Database Systems: The Complete Book", second edition, page 26 for more
 * information on the Movie Database schema.
 */
class MovieDB
{
    /*************************************************************************************
     * Main method for creating, populating and querying a Movie Database.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        out.println ();

        var movie = new Table ("movie", "title year length genre studioName producerNo",
                                        "String Integer Integer String String Integer", "title year");

        var cinema = new Table ("cinema", "title year length genre studioName producerNo",
                                          "String Integer Integer String String Integer", "title year");

        var movieStar = new Table ("movieStar", "name address gender birthdate",
                                                "String String Character String", "name");

        var starsIn = new Table ("starsIn", "movieTitle movieYear starName",
                                            "String Integer String", "movieTitle movieYear starName");

        var movieExec = new Table ("movieExec", "certNo name address fee",
                                                "Integer String String Float", "certNo");

        var studio = new Table ("studio", "name address presNo",
                                          "String String Integer", "name");

        var film0 = new Comparable [] { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
        var film1 = new Comparable [] { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
        var film2 = new Comparable [] { "Rocky", 1985, 200, "action", "Universal", 12125 };
        var film3 = new Comparable [] { "Rambo", 1978, 100, "action", "Universal", 32355 };

        out.println ();
        movie.insert (film0);
        movie.insert (film1);
        movie.insert (film2);
        movie.insert (film3);
        movie.print ();

        var film4 = new Comparable [] { "Galaxy_Quest", 1999, 104, "comedy", "DreamWorks", 67890 };
        out.println ();
        cinema.insert (film2);
        cinema.insert (film3);
        cinema.insert (film4);
        cinema.print ();

        var star0 = new Comparable [] { "Carrie_Fisher", "Hollywood", 'F', "9/9/99" };
        var star1 = new Comparable [] { "Mark_Hamill", "Brentwood", 'M', "8/8/88" };
        var star2 = new Comparable [] { "Harrison_Ford", "Beverly_Hills", 'M', "7/7/77" };
        out.println ();
        movieStar.insert (star0);
        movieStar.insert (star1);
        movieStar.insert (star2);
        movieStar.print ();

        var cast0 = new Comparable [] { "Star_Wars", 1977, "Carrie_Fisher" };
        out.println ();
        starsIn.insert (cast0);
        starsIn.print ();

        var exec0 = new Comparable [] { 9999, "S_Spielberg", "Hollywood", 10000.00 };
        out.println ();
        movieExec.insert (exec0);
        movieExec.print ();

        var studio0 = new Comparable [] { "Fox", "Los_Angeles", 7777 };
        var studio1 = new Comparable [] { "Universal", "Universal_City", 8888 };
        var studio2 = new Comparable [] { "DreamWorks", "Universal_City", 9999 };
        out.println ();
        studio.insert (studio0);
        studio.insert (studio1);
        studio.insert (studio2);
        studio.print ();

       /* movie.save ();
        cinema.save ();
        movieStar.save ();
        starsIn.save ();
        movieExec.save ();
        studio.save ();*/
       
        /*movieStar.printIndex ();*/

        //--------------------- project: title year

        out.println ();
        var t_project = movie.project ("title year");
        try 
        {
        	t_project.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as table or attribute is incompatible for project operation");
        }

        //--------------------- select: equals, &&

        out.println ();
        var t_select = movie.select (t -> t[movie.col("title")].equals ("Star_Wars") &&
                                            t[movie.col("year")].equals (1977));
        t_select.print ();

        //--------------------- select: <

        out.println ();
        var t_select2 = movie.select (t -> (Integer) t[movie.col("year")] < 1980);
        t_select2.print ();

        //--------------------- indexed select: key

        out.println ();
        var t_iselect = movieStar.select (new KeyType ("Harrison_Ford"));
        try 
        {
        	t_iselect.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as table or key is incompatible for select operation");
        }
        
        //--------------------- union: movie UNION cinema

        out.println ();
        var t_union = movie.union (cinema);
        try 
        {
        	t_union.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as tables are incompatible for union operation");
        }
        
        

        //--------------------- minus: movie MINUS cinema

        out.println ();
        var t_minus = movie.minus (cinema);
        try 
        {
        	t_minus.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as tables are incompatible for minus operation");
        }

        //--------------------- equi-join: movie JOIN studio ON studioName = name

        out.println ();
        var t_join = movie.join ("studioName", "name", studio);
        try 
        {
        	t_join.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as tables are incompatible for equi-join operation");
        }
        
      //--------------------- equi-join: movie i_JOIN studio ON studioName = name
        out.println ();
        var I_join = movie.i_join ("studioName", "name", studio);
        try 
        {
        	I_join.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as tables are incompatible for equi-join operation");
        }

        //--------------------- natural join: movie JOIN studio

        out.println ();
        var t_join2 = movie.join (cinema);
        try 
        {
        	t_join2.print ();	
        }
        catch(Exception e)
        {
        	System.out.println("couldn't execute as tables are incompatible for natural join operation");
        }

    } // main

} // MovieDB class
