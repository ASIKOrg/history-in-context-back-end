package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringEncoder
{
    /**
     * Takes a URL encoded string and decodes it.
     * 
     * @param unparsedString
     * @return
     */
    public static String decodeGET(String unparsedString)
    {
        String decoded = unparsedString;
        try
        {
            decoded = URLDecoder.decode(unparsedString, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decoded;
    }

    /**
     * Takes a URL encoded string and decodes it.
     * 
     * @param unparsedString
     * @return
     */
    public static String encodeGET(String unparsedString)
    {
        String encoded = unparsedString;
        try
        {
            encoded = URLEncoder.encode(unparsedString, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encoded;
    }
}
