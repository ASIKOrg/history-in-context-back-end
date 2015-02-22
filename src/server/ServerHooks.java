package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import server.nanohttpd.NanoHTTPD.Response;

/**
 *
 */
public class ServerHooks
{

    // Objects
    private static ServerHooks s = null;

    // used in get/set vars calls
    private static Map<String, String> vars = Collections.synchronizedMap(new LinkedHashMap<String, String>());

    // used to hold mimetypes
    private Map<String, String> mimeTypes = new LinkedHashMap<String, String>();

    // default mimetype
    private final String DEFAULT_MIME_TYPE = "text/html";

    // XSS vals
    private final String XSS_KEY = "Access-Control-Allow-Origin";
    private final String XSS_VALUE = "*";

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
    }

    /**
     * Simply serves the site page
     */
    public Response servePage(String uri)
    {
        Response response = new Response("");
        try
        {
            // enable XSS
            response.addHeader(XSS_KEY, XSS_VALUE);
            response.setData(new FileInputStream(WebServer.PAGE_TO_SERVE));
            response.setMimeType(mimeTypes.getOrDefault("html", DEFAULT_MIME_TYPE));
        }
        catch (FileNotFoundException e)
        {
            System.err.println(WebServer.PAGE_TO_SERVE + " is not found");
            return new Response(Response.Status.INTERNAL_ERROR, DEFAULT_MIME_TYPE, "Failed to load page.");
        }
        return response;
    }

    /**
     * Calls the API
     */
    public Response callAPI(String uri)
	{
	    Response response = new Response(Response.Status.ACCEPTED, mimeTypes.getOrDefault("html", DEFAULT_MIME_TYPE),"API CALLED!");
        //enable XSS
        response.addHeader(XSS_KEY, XSS_VALUE);	    
	    return response;
	}
}
