package top.jxau.support.core.io;

import top.jxau.exceptions.ResourceException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author plutohh
 */
public class UrlResource implements Resource{

    private final URL url;

    public UrlResource(URL url) {
        if(url == null) {
            throw new ResourceException("URL must not be null");
        }
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        }
        catch (IOException ex){
            if (con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }

}
