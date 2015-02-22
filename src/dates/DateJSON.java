package dates;

import org.json.simple.JSONObject;

public class DateJSON
{
    private String START_KEY = "start";
    private String END_KEY = "end";

    private Date date = null;
    private JSONObject json = null;

    public DateJSON(Date date)
    {
        json = new JSONObject();
        if (date != null)
        {
            this.date = date;            
            json.put(START_KEY, date.startYearInt);
            json.put(END_KEY, date.endYearInt);
        }
        else
        {
            json.put(START_KEY, "Error!");
            json.put(END_KEY, "Error!");
        }
    }

    @Override
    public String toString()
    {
        return json.toString();
    }

}
