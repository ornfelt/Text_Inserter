package file;

/**
 *
 * @author jonas
 */
public class RubberLang extends MessageDecorator {

    TextMessage textMessage;

    public RubberLang(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    //encodes to rubber language
    public String getMessage() {
        this.message = textMessage.getMessage();
        String vowels = "aeyuoåäöi";
        String outString = "";
        int length = message.length();
        for (int i = 0; i < length; i++) {
            if (vowels.contains(message.toLowerCase().charAt(i) + "")) {
                char c = message.charAt(i);
                outString += c + "o" + c;
            } else {
                outString += message.charAt(i);
            }
        }
        return outString;
    }
}
