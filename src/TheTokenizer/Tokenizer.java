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

    public class Token {                            // Token class to hold tokens
        public final int token;
        public final String sequence;

        public Token(int token, String sequence){   // Constructor of Token class
            super();
            this.token = token;
            this.sequence = sequence;
        }
    }
                                                    // Declaration of LinkedList
    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {                            // Constructor of class Tokenizer
        tokenInfos = new LinkedList<TokenInfo>();   // Initialising of Linked list for TokenInfo
        tokens = new LinkedList<Token>();           // Initialising of Linked list for Tokens
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
        String s = new String(str);
        tokens.clear();
       // try {
            while (!s.equals("")) {
                boolean match = false;

                for (TokenInfo info : tokenInfos) {
                    Matcher m = info.regex.matcher(s);
                    if (m.find()) {
                        match = true;

                        String tok = m.group().trim();
                        Token objectToken = new Token(info.token, tok);
                        tokens.add(objectToken);

                        s = m.replaceFirst("").trim();
                        break;
                    }
                }

                if (!match) throw new ParserException("Unexpected character in the input: " + s);
                    //System.out.println("Unexpected character in input: " + s);
            }
        //}

        //catch (ParserConfigurationException e) {
         //   System.out.println(e.getMessage());
        //}
    }


    // GETTOKENS METHOD

    public LinkedList<Token> getTokens() {
        return tokens;
    }


    //MAIN METHOD

    public static void main(String[] args){
        Tokenizer tokenizer = new Tokenizer();                      // Object of Tokenizer class
        tokenizer.addRegEx("sin|cos|exp|ln|sqrt",1);         // Grammar for functions
        tokenizer.addRegEx("\\(",2);                         // Grammar for open brackets
        tokenizer.addRegEx("\\)",3);                         // Grammar for close brackets
        tokenizer.addRegEx("[+-]",4);                        // Grammar for plus or minus
        tokenizer.addRegEx("[*/]",5);                        // Grammar for multiplication or division
        tokenizer.addRegEx("\\^",6);                         // Grammar for raised numbers
        tokenizer.addRegEx("[0-9]+",7);                      // Grammar for accepting integer numbers
        tokenizer.addRegEx("[a-z][a-zA-Z0-9_]*",8);       // Grammar for accepting variables
        try {
            tokenizer.tokenize("var_a + (5)");

            for (Tokenizer.Token tok : tokenizer.getTokens()) {
                System.out.println(tok.sequence + " -> " + tok.token);
            }
        }
        catch (ParserException e){
            System.out.println(e.getMessage());
        }
    }

}

// int sin;
