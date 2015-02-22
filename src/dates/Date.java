package dates;

import parser.Cleaner;

public class Date
{
    int startYearInt = 0;
    int endYearInt = 0;

    /**
     * Object to contain scraped date.
     * 
     * @param startYear
     * @param endYear
     */
    public Date(String startYear, String endYear)
    {
        startYear = Cleaner.cleanDate(startYear);
        endYear = Cleaner.cleanDate(endYear);
        try
        {
            startYearInt = Integer.parseInt(startYear.trim());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            startYearInt = 0;
        }
        try
        {
            endYearInt = Integer.parseInt(endYear.trim());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            endYearInt = 0;
        }
//        endYearInt = (startYearInt > endYearInt)? startYearInt: endYearInt;
    }

    /**
     * Start year integer
     * 
     * @return
     */
    public int startYear()
    {
        return startYearInt;
    }

    /**
     * end year integer
     * 
     * @return
     */
    public int endYear()
    {
        return endYearInt;
    }
}
