package wicket.contrib.examples.gmap;

import wicket.contrib.examples.WicketExamplePage;
import wicket.contrib.gmap.GMap;
import wicket.contrib.gmap.GMapPanel;
import wicket.contrib.gmap.GMarker;
import wicket.contrib.gmap.GPoint;

/**
 * @author Iulian-Corneliu COSTAN
 */
public class HomePage extends WicketExamplePage
{

    public HomePage()
    {
        // add gmap
        GMap gmap = new GMap(new GPoint(10, 30), 15);
        gmap.setHasTypeControl(true);
        gmap.setHasSmallMapControl(true);

        // www.wicket.org
        GMarker overlay = new GMarker(new GPoint(-78.7073f, 35.7512f));
        overlay.setOnClickMessage("<a href=\"http://wicket.sourceforge.net/\">http://wicket.sourceforge.net/</a>");
        gmap.addOverlay(overlay);

        // www.wicket-library.com
        overlay = new GMarker(new GPoint(-112.1872f, 33.2765f));
        overlay.setOnClickMessage("<a href=\"http://www.wicket-library.com/\">www.wicket-library.com/</a>");
        gmap.addOverlay(overlay);

        add(new GMapPanel("gmap", gmap, 800, 600, LOCALHOST_8080_WICKET_CONTRIB_GMAP_KEY));
    }

    //pay attention at webapp deploy context, we need a different key for each deploy context
    //check <a href="http://www.google.com/apis/maps/signup.html">Google Maps API - Sign Up</a> for more info

    //key for http://localhost:8080/wicket-contrib-gmap, deploy context is wicket-contrib-gmap
    private static final String LOCALHOST_8080_WICKET_CONTRIB_GMAP_KEY = "ABQIAAAALjfJpigGWq5XvKwy7McLIxTDxbH1TVfo7w-iwzG2OxhXSIjJdhQTwgha-mCK8wiVEq4rgi9qvz8HYw";

    //key for http://localhost:8080/gmap, deploy context is gmap
    private static final String GMAP_8080_KEY = "ABQIAAAALjfJpigGWq5XvKwy7McLIxTh_sjBSLCHIDZfjzu1cFb3Pz7MrRQLOeA7BMLtPnXOjHn46gG11m_VFg";

    //key for http://localhost/gmap
    private static final String GMAP_DEFAULT_KEY = "ABQIAAAALjfJpigGWq5XvKwy7McLIxTIqKwA3nrz2BTziwZcGRDeDRNmMxS-FtSv7KGpE1A21EJiYSIibc-oEA";

    //key for http://www.wicket-library.com/wicket-examples/
    private static final String WICKET_LIBRARY_KEY = "ABQIAAAALjfJpigGWq5XvKwy7McLIxQTV35WN9IbLCS5__wznwqtm2prcBQxH8xw59T_NZJ3NCsDSwdTwHTrhg";
}
