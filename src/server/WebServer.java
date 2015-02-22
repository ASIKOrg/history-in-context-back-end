/*
 This file is part of theunibot.

 theunibot is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 theunibot is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with theunibot.  If not, see <http://www.gnu.org/licenses/>.

 Copyright (c) 2014 Unidesk Corporation
 */
package server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeType;
import server.nanohttpd.NanoHTTPD;
import static server.nanohttpd.NanoHTTPD.MIME_HTML;
import static server.nanohttpd.NanoHTTPD.MIME_PLAINTEXT;

/**
 *
 */
public class WebServer extends NanoHTTPD {

	private static final int PORT = 8080;

	private final String ROOT_DIR = "./webRootFolder";

	private final String API_SUBDIR = "/api";

	private Map<String, String> mimeTypes = new LinkedHashMap<String, String>();

	private final String[] MAIN
		= {
			"/", ""
		};
	private final String ENQUEUE = "/ENQUEUE";
	private final String STATUS = "/STATUS";
	private final String CLEAR_QUEUE = "/CLEAR-QUEUE";
	private final String GET_VAR = "/GET-VARIABLE";
	private final String SET_VAR = "/SET-VARIABLE";
	private final String INVENTORY = "/INVENTORY";
	private final String DEBUG = "/DEBUG";

	private final String XSS_KEY = "Access-Control-Allow-Origin";
	private final String XSS_VALUE = "*";

	//serve pages
	private final String PAGE_TO_SERVE = ROOT_DIR + "/test.html";

	private ServerHooks sh = null;

	private String mimeType = MIME_PLAINTEXT;

	public WebServer() {
		super(PORT);
		sh = ServerHooks.getInstance();
		mimeTypes.put("js", "application/javascript");
		mimeTypes.put("html", MIME_HTML);
		mimeTypes.put("htm", MIME_HTML);
		mimeTypes.put("json", "application/json");
	}

	@Override
	public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
		Response response = new Response("");
		response.addHeader(XSS_KEY, XSS_VALUE);		
		try
        {
            response.setData(new FileInputStream(PAGE_TO_SERVE));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
		NanoHTTPD.Method method = session.getMethod();
		return response;
	}

}
//else if (uri.startsWith(FAVICON))
//            {
//                System.out.println("Loading favicon: " + ROOT_DIR + "/favicon.ico");
//                try
//                {
//                    return new NanoHTTPD.Response(NanoHTTPD.Response.Status.ACCEPTED, "image/x-icon", new FileInputStream(ROOT_DIR + "/favicon.ico"));//
//                }
//                catch (FileNotFoundException ex)
//                {
//                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
