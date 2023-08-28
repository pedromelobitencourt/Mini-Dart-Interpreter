package lexical;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS
        st.put("?", TokenType.NULLABLE);
        st.put(",", TokenType.COMMA);
        st.put(":", TokenType.COLON);
        st.put(";", TokenType.SEMICOLON);
        st.put("(", TokenType.OPEN_PAR);
        st.put(")", TokenType.CLOSE_PAR);
        st.put("{", TokenType.OPEN_CUR);
        st.put("}", TokenType.CLOSE_CUR);
        st.put("[", TokenType.OPEN_BRA);
        st.put("]", TokenType.CLOSE_BRA);
        st.put("=", TokenType.ASSIGN);
        st.put("in", TokenType.IN);

        // OPERATORS
        st.put("??", TokenType.IF_NULL);
        st.put("&&", TokenType.AND);
        st.put("||", TokenType.OR);
        st.put("<", TokenType.LOWER_THAN);
        st.put(">", TokenType.GREATER_THAN);
        st.put("<=", TokenType.LOWER_EQUAL);
        st.put(">=", TokenType.GREATER_EQUAL);
        st.put("==", TokenType.EQUAL);
        st.put("!=", TokenType.NOT_EQUAL);
        st.put("+", TokenType.ADD);
        st.put("-", TokenType.SUB);
        st.put("*", TokenType.MUL);
        st.put("/", TokenType.DIV);
        st.put("%", TokenType.MOD);
        st.put("!", TokenType.NOT);
        st.put("++", TokenType.INC);
        st.put("--", TokenType.DEC);
        st.put("...", TokenType.SPREAD);

        // KEYWORDS
        st.put("final", TokenType.FINAL);
        st.put("var", TokenType.VAR);
        st.put("print", TokenType.PRINT);
        st.put("assert", TokenType.ASSERT);
        st.put("if", TokenType.IF);
        st.put("else", TokenType.ELSE);
        st.put("while", TokenType.WHILE);
        st.put("do", TokenType.DO);
        st.put("for", TokenType.FOR);
        st.put("null", TokenType.NULL);
        st.put("false", TokenType.FALSE);
        st.put("true", TokenType.TRUE);
        st.put("read", TokenType.READ);
        st.put("random", TokenType.RANDOM);
        st.put("length", TokenType.LENGTH);
        st.put("keys", TokenType.KEYS);
        st.put("values", TokenType.VALUES);
        st.put("tobool", TokenType.TOBOOL);
        st.put("toint", TokenType.TOINT);
        st.put("tostr", TokenType.TOSTR);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ? st.get(token) : TokenType.NAME;
    }
}
