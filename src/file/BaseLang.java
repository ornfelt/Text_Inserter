package file;

import java.util.Base64;

/**
 *
 * @author jonas
 */
public class BaseLang extends MessageDecorator {

    TextMessage textMessage;

    public BaseLang(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    //encodes to Base64
    public String getMessage() {
        this.message = textMessage.getMessage();
        return Base64.getEncoder().encodeToString(this.message.getBytes());
    }
}
