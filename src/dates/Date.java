package dates;

public class Date
{
    int startYearInt = 0;
    int endYearInt = 0;
    
    /**
     * Object to contain scraped date.
     * @param startYear
     * @param endYear
     */
    public Date(String startYear, String endYear)
    {
        startYearInt = Integer.parseInt(startYear);
        endYearInt = Integer.parseInt(endYear);
    }
    
    /**
     * Start year integer
     * @return
     */
    public int startYear()
    {
        return startYearInt;
    }
    
    /**
     * end year integer
     * @return
     */
    public int endYear()
    {
        return endYearInt;
    }
}
