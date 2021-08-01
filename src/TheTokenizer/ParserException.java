package TheTokenizer;

public class ParserException extends RuntimeException {

    private static final long serialVersionUID = -1009747984332258423L;
    private Token token = null;
    public ParserException(String msg){
        super(msg);
    }

    public ParserException(String message, Token token){
        super(message);
        this.token = token;
    }

    public Token getToken(){
        return token;
    }

    public String getMessage(){
        String msg = super.getMessage();
        if (token != null){
            msg = msg.replace("%s", token.sequence);
        }
        return msg;
    }
}
