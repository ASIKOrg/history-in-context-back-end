package wikipedia;

import utils.StringEncoder;

public class WikipediaRequest
{
    private final String BASE_URL = "http://en.wikipedia.org/wiki/";
    
    private String pageName = null;
    private String url = null;
    
    /**
     * Object for translating a desired page name into a Wikipedia request.
     * @param pageName
     */
    public WikipediaRequest(String pageName)
    {
        this.pageName = pageName;
        this.url = generateURL(pageName);
    }
    
    /**
     * Generates the URL for the Wikipedia request.
     * @param pageName
     * @return
     */
    private String generateURL(String pageName)
    {
        //clean/encode pageName
        System.out.println("Unencoded pageName: " + pageName);                
        pageName = StringEncoder.encodeGET(pageName.trim().replace(" ", "_"));
        System.out.println("Encoded pageName: " + pageName);
        return BASE_URL + pageName;
    }
    
    @Override
    public String toString()
    {        
        return this.url;
    }
}
