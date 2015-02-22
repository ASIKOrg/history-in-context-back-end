package main;

import parser.Cleaner;
import server.WebServer;
import server.nanohttpd.ServerRunner;
import wikipedia.WikipediaHTTPRequest;
import wikipedia.WikipediaRequest;


public class Main
{
	// Global variables
	static int[] in;
	static int[] out;
	
	public static final boolean DEBUG = true;
	
    public static void main(String[] args)
    {        
        System.out.println("Starting...");
        // start up the web server
        ServerRunner.run(WebServer.class);        
    }

}
