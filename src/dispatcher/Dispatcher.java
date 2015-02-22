package dispatcher;

import utils.StringEncoder;
import wikipedia.WikipediaHTTPRequest;
import wikipedia.WikipediaRequest;

public class Dispatcher
{
    private static Dispatcher dispatcher = null;
    
    /**
     * Singleton instance of dispatcher.
     * @return
     */
    public static Dispatcher getInstance()
    {
        if(dispatcher == null)
        {
            dispatcher = new Dispatcher();
        }
        return dispatcher;
    }
    
    /**
     * Executes the API and returns the API response data.
     * @return
     */
    public String executeAPI(String uri, String inputVal)
    {        
        //error check
        if(inputVal != null)
        {
            System.out.println("URI:          <" + uri + ">");
            System.out.println("Input Val:    <" + inputVal + ">");
            String cleanInputVal = StringEncoder.decodeGET(inputVal);
            System.out.println("Clean Input:  <" + cleanInputVal + ">");
            
            //create wikipedia object
            WikipediaRequest wikiReq = new WikipediaRequest(cleanInputVal);
            System.out.println("Sending Wiki request to: " + wikiReq);
            
            //execute HTTP request
            String pageHTML = WikipediaHTTPRequest.sendGet(wikiReq);
            
        }
        else
        {
            System.err.println("Input Value null");
            return "ERROR: Input value not found!";
        }
        return "API!!!";
    }
}
