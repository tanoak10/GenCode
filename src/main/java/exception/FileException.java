package exception;

import java.io.Serializable;

/**
 * 文件异常
 *
 * @author tanoak
 * @date 2019/12/26
 */
public class FileException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }
}
