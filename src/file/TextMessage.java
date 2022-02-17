package file;

/**
 *
 * @author jonas
 */
public abstract class TextMessage {
    
    String message = "No message";
    /*public TextMessage(String message){
        this.message = message;
    } */
    
    public String getMessage(){
        return message;
    }
}
