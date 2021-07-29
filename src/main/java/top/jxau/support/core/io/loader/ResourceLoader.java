package top.jxau.support.core.io.loader;


import top.jxau.support.core.io.Resource;

/**
 * @author plutohh
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据路径获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);

}
