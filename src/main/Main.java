package main;

import server.WebServer;
import server.nanohttpd.ServerRunner;


public class Main
{
	// Global variables
	static int[] in;
	static int[] out;
	
	public static final boolean DEBUG = false;
	
    public static void main(String[] args)
    {
        System.out.println("Starting...");
        // start up the web server
        ServerRunner.run(WebServer.class);
    }

}
