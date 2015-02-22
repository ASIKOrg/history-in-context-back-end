package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import dispatcher.Dispatcher;
import server.nanohttpd.NanoHTTPD.Response;

/**
 *
 */
public class ServerHooks
{

    // Objects
    private static ServerHooks s = null;
    
    private Dispatcher dispatcher = Dispatcher.getInstance();

    // used to hold mimetypes
    private Map<String, String> mimeTypes = new LinkedHashMap<String, String>();

    // default mimetype
    private final String DEFAULT_MIME_TYPE = "text/html";

    // XSS vals
    private final String XSS_KEY = "Access-Control-Allow-Origin";
    private final String XSS_VALUE = "*";
    
    private final String INPUT_KEY = "search";

    /**
     * Singleton instance of ServerHooks
     * 
     * @return
     */
    public static ServerHooks getInstance()
    {
        if (s == null)
            s = new ServerHooks();
        return s;
    }

    private ServerHooks()
    {        
        mimeTypes.put("js", "application/javascript");
        mimeTypes.put("html", "text/html");
        mimeTypes.put("htm", "text/html");
        mimeTypes.put("json", "application/json");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("png", "image/png");        
    }

    /**
     * Simply serves the site page
     */
    public Response servePage(String uri)
    {        
        Response response = new Response("");
        String mimeType = DEFAULT_MIME_TYPE;
        System.out.println("URI: " + uri);
        if(uri.toLowerCase().contains("css"))
        {
            System.out.println("CSS");
            mimeType = mimeTypes.get("css");
        }
        else if(uri.toLowerCase().contains("js"))
        {
            System.out.println("JS");
            mimeType = mimeTypes.get("js");
        }
        else if(uri.toLowerCase().contains("png"))
        {
            System.out.println("PNG");
            mimeType = mimeTypes.get("png");
        }            
        try
        {
            // enable XSS
            response.addHeader(XSS_KEY, XSS_VALUE);
            response.setData(new FileInputStream(WebServer.ROOT_DIR + "/" + uri));
            response.setMimeType(mimeType);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Page is not found");
            return new Response(Response.Status.INTERNAL_ERROR, DEFAULT_MIME_TYPE, "Failed to load page.");
        }
        return response;
    }

    /**
     * Calls the API
     */
    public Response callAPI(String uri, Map<String, String> params)
	{
        System.out.println("API CLLLLLLLLLLLLLLLLL");        
        String inputVal = params.get(INPUT_KEY);
        System.out.println("INPUT VALLLL " + inputVal);
        String apiResponse = dispatcher.executeAPI(uri, inputVal);
	    Response response = new Response(Response.Status.ACCEPTED, mimeTypes.getOrDefault("json", DEFAULT_MIME_TYPE), apiResponse);
        //enable XSS
        response.addHeader(XSS_KEY, XSS_VALUE);	    
	    return response;
	}
}
