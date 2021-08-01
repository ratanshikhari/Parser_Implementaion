package TheTokenizer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private class TokenInfo{
        public final Pattern regex; // Pattern is a class, regex is the object of class Pattern
        public final int token;     // token is the value of the Token
        //int Sin;

        public TokenInfo(Pattern regex, int token){  // Constructor of TokenInfo class
            super();
            this.regex = regex;
            this.token = token;
        }
    }

                                                    // Declaration of LinkedList
    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;
    private static Tokenizer expressionTokenizer = null;

    public Tokenizer() {                            // Constructor of class Tokenizer
        tokenInfos = new LinkedList<TokenInfo>();   // Initialising of Linked list for TokenInfo
        tokens = new LinkedList<Token>();           // Initialising of Linked list for Tokens
    }

    public static Tokenizer getExpressionTokenizer() {
        if (expressionTokenizer == null)
            expressionTokenizer = createExpressionTokenizer();
        return expressionTokenizer;
    }

    private static Tokenizer createExpressionTokenizer()
    {
        Tokenizer tokenizer = new Tokenizer();

        tokenizer.addRegEx("[+-]", Token.PLUSMINUS);
        tokenizer.addRegEx("[*/]", Token.MULTIDIV);
        tokenizer.addRegEx("\\^", Token.RAISED);

        String funcs = FunctionExpressionNode.getAllFunctions();
        tokenizer.addRegEx("(" + funcs + ")(?!\\w)", Token.FUNCTION);

        tokenizer.addRegEx("\\(", Token.OPEN_BRACKET);
        tokenizer.addRegEx("\\)", Token.CLOSE_BRACKET);
        tokenizer.addRegEx("(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?", Token.NUMBER);
        tokenizer.addRegEx("[a-zA-Z]\\w*", Token.VARIABLE);

        return tokenizer;
    }

    public void addRegEx(String regex, int token){       // Adding regex and token to the LinkedList tokenInfos
        TokenInfo objectTokenInfo = new TokenInfo( Pattern.compile("^(" + regex + ")"), token);     // Creating the object of TokenInfo and passing the regex and token
        tokenInfos.add(objectTokenInfo);            // Adding to LinkedList using .add() method
    }
    // The user can pass a regular expression string and a token code to the method,
    // the method will then the “^” character to the user supplied regular expression,
    // it causes the regular expression to match only the beginning of a string,
    // this is needed because we will be removing any token always looking for the next token at the beginning of the input string.


    // TOKENIZE METHOD

    public void tokenize(String str){
        String s = str.trim();
        int totalLength = s.length();
        tokens.clear();
        while (!s.equals("")) {
            int remaining = s.length();
            boolean match = false;

            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    Token objectToken = new Token(info.token, tok, totalLength - remaining);
                    tokens.add(objectToken);

                    s = m.replaceFirst("").trim();
                    break;
                }
            }

            if (!match) throw new ParserException("Unexpected character in the input: " + s);
        }
    }


    // GETTOKENS METHOD

    public LinkedList<Token> getTokens() {
        return tokens;
    }
}

