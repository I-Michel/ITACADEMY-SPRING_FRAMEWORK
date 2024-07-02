package DiceGame.S05T02Michel.model.exception;

public class ErrorDetails {

    private int statusCode;
    private String message;
    private String description;
    public ErrorDetails(int statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getMessage() {
        return description;
    }
    public String getDescription() {
        return description;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
