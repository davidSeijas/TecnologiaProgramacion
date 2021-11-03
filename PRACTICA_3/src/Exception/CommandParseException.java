package Exception;

public class CommandParseException extends Exception{
	//private String message;
	
	public CommandParseException(String message) {
		super(message);
		//this.message = message;
	}

//	public String getMessage() {
//		return message;
//	}
}