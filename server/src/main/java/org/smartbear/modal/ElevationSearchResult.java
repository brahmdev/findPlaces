package org.smartbear.modal;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
public class ElevationSearchResult {
	private Location location;

    private String elevation;

    private String resolution;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getElevation ()
    {
        return elevation;
    }

    public void setElevation (String elevation)
    {
        this.elevation = elevation;
    }

    public String getResolution ()
    {
        return resolution;
    }

    public void setResolution (String resolution)
    {
        this.resolution = resolution;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", elevation = "+elevation+", resolution = "+resolution+"]";
    }
}
