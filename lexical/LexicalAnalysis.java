package lexical;

import java.io.FileInputStream;
import java.io.PushbackInputStream;

public class LexicalAnalysis implements AutoCloseable {

    private int line;
    private SymbolTable st;
    private PushbackInputStream input;

    public LexicalAnalysis(String filename) {
        try {
            input = new PushbackInputStream(new FileInputStream(filename), 2);
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        st = new SymbolTable();
        line = 1;
    }

    public void close() {
        try {
            input.close();
        } catch (Exception e) {
            throw new LexicalException("Unable to close file");
        }
    }

    public int getLine() {
        return this.line;
    }

    public Lexeme nextToken() {
        Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);

        int state = 1;
        while (state != 15 && state != 16) {
            int c = getc();
            //System.out.printf("  [%02d, %03d ('%c')]\n", state, c, (char) c);
            //System.out.println(lex.token);

            switch (state) {
                case 1:
                    if (c == ' ' || c == '\t' || c == '\r') {
                        state = 1;
                    } else if (c == '\n') {
                        state = 1;
                        this.line++;
                    } else if (c == '/') {
                        lex.token += (char) c;
                        state = 2;
                    } else if (c == '=' || c == '!' || c == '<' || c == '>') {
                        lex.token += (char) c;
                        state = 4;
                    } else if (c == '+') {
                        lex.token += (char) c;
                        state = 5;
                    } else if (c == '-') {
                        lex.token += (char) c;
                        state = 6;
                    } else if (c == '?') {
                        lex.token += (char) c;
                        state = 7;
                    } else if (c == ',' || c == ':' || c == ';' ||
                            c == '(' || c == ')' || c == '{' || c == '}' ||
                            c == '[' || c == ']' || c == '*' || c == '%') {
                        lex.token += (char) c;
                        state = 15;
                    } else if (c == '.') {
                        lex.token += (char) c;
                        state = 8;
                    } else if (c == '&') {
                        lex.token += (char) c;
                        state = 10;
                    } else if (c == '|') {
                        lex.token += (char) c;
                        state = 11;
                    } else if (c == '_' || c == '$' ||
                        Character.isLetter(c)) {
                        lex.token += (char) c;
                        state = 12;
                    } else if (Character.isDigit(c)) {
                        lex.token += (char) c;
                        state = 13;
                    } else if (c == '\'') {
                        state = 14;
                    } else if (c == -1) {
                        lex.type = TokenType.END_OF_FILE;
                        state = 16;
                    } else {
                        lex.token += (char) c;
                        lex.type = TokenType.INVALID_TOKEN;
                        state = 16;
                    }

                    break;
                case 2:
                    if (c == '/'){
                        state = 3;
                        lex.token = "";
                    }
                    else {
                        ungetc(c);
                        state = 15;
                    }
                    break;
                case 3:
                    if (c == '\n') {
                        state = 1;
                        this.line++;
                    }
                    break;
                case 4:
                    if (c == '=') {
                        lex.token += (char) c;
                        state = 15;
                    } else {
                        ungetc(c);
                        state = 15;
                    }

                    break;
                case 5:
                    if (c == '+') {
                        lex.token += (char) c;
                        state = 15;
                    } else {
                        ungetc(c);
                        state = 15;
                    }

                    break;
                case 6:
                    if (c == '-') {
                        lex.token += (char) c;
                        state = 15;
                    } else {
                        ungetc(c);
                        state = 15;
                    }
                    break;
                case 7:
                    if (c == '?') {
                        lex.token += (char) c;
                        state = 15;
                    } else {
                        ungetc(c);
                        state = 15;
                    }
                    break;
                case 8:
                    if (c == '.') {
                        lex.token += (char) c;
                        state = 9;
                    }
                    break;
                case 9:
                    if (c == '.') {
                        lex.token += (char) c;
                        state = 15;
                    }
                    break;
                case 10:
                    if (c == '&') {
                        lex.token += (char) c;
                        state = 15;
                    } else {
                        lex.type = TokenType.INVALID_TOKEN;
                        state = 16;
                    }

                    break;
                case 11:
                    if(c == '|') {
                        lex.token += (char) c;
                        state = 15;
                    }
                    break;
                case 12:
                    if (c == '_' || c == '$' ||
                        Character.isLetter(c) ||
                        Character.isDigit(c)) {
                        lex.token += (char) c;
                        state = 12;
                    } else {
                        ungetc(c);
                        state = 15;
                    }

                    break;
                case 13:
                    if(!(Character.isDigit(c))){
                        ungetc(c);
                        lex.type = TokenType.NUMBER;
                        state = 16;
                    }
                    else {
                        lex.token += (char) c;
                    }
                    break;
                case 14:
                    if(c == '\'') {
                        lex.type = TokenType.TEXT;
                        state = 16;
                    }
                    else
                        lex.token += (char) c;
                    break;
                default:
                    throw new LexicalException("Unreachable");
            }
        }

        if (state == 15)
            lex.type = st.find(lex.token);

        return lex;
    }

    private int getc() {
        try {
            return input.read();
        } catch (Exception e) {
            throw new LexicalException("Unable to read file");
        }
    }

    private void ungetc(int c) {
        if (c != -1) {
            try {
                input.unread(c);
            } catch (Exception e) {
                throw new LexicalException("Unable to ungetc");
            }
        }
    }
}
