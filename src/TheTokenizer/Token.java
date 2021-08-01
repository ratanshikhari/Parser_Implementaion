package TheTokenizer;

public class Token {
    public static final int EPSILON = 0;
    public static final int PLUSMINUS = 1;
    public static final int MULTIDIV = 2;
    public static final int RAISED = 3;
    public static final int FUNCTION = 4;
    public static final int OPEN_BRACKET = 5;
    public static final int CLOSE_BRACKET = 6;
    public static final int NUMBER = 7;
    public static final int VARIABLE = 8;

    public final int token;
    public final String sequence;
    public final int pos;

//    public Token(int token, String sequence) {   // Constructor of Token class
//        super();
//        this.token = token;
//        this.sequence = sequence;
//    }

    public Token(int token, String sequence, int pos) {
        super();
        this.token = token;
        this.sequence = sequence;
        this.pos = pos;
    }
}
