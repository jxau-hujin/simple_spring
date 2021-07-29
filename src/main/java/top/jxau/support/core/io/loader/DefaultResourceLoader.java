package top.jxau.support.core.io.loader;

import org.apache.commons.lang3.StringUtils;
import top.jxau.exceptions.ResourceException;
import top.jxau.support.core.io.ClassPathResource;
import top.jxau.support.core.io.FileSystemResource;
import top.jxau.support.core.io.Resource;
import top.jxau.support.core.io.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author plutohh
 */
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        if(!StringUtils.isNotBlank(location)) {
            throw new ResourceException("location must not be null");
        }
        if(location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try {
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            // TODO 日志
        }
        return new FileSystemResource(location);
    }
}
