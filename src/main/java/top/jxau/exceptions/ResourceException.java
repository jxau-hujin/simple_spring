package top.jxau.exceptions;

/**
 * @author plutohh
 */
public class ResourceException extends RuntimeException{

    public ResourceException(String msg) {
        super(msg);
    }

    public ResourceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
