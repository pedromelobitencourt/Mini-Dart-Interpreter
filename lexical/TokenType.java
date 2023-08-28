package lexical;

public enum TokenType {
    // SPECIALS
    UNEXPECTED_EOF,
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS
    NULLABLE,      // ?
    COMMA,         // ,
    COLON,         // :
    SEMICOLON,     // ;
    OPEN_PAR,      // (
    CLOSE_PAR,     // )
    OPEN_CUR,      // {
    CLOSE_CUR,     // }
    OPEN_BRA,      // [
    CLOSE_BRA,     // ]

    // OPERATORS
    ASSIGN,        // =
    IN,            // in
    IF_NULL,       // ??
    AND,           // &&
    OR,            // ||
    LOWER_THAN,    // <
    GREATER_THAN,  // >
    LOWER_EQUAL,   // <=
    GREATER_EQUAL, // >=
    EQUAL,         // ==
    NOT_EQUAL,     // !=
    ADD,           // +
    SUB,           // -
    MUL,           // *
    DIV,           // /
    MOD,           // %
    NOT,           // !
    INC,           // ++
    DEC,           // --
    SPREAD,        // ...

    // KEYWORDS
    FINAL,         // final
    VAR,           // var
    PRINT,         // print
    ASSERT,        // assert
    IF,            // if
    ELSE,          // else
    WHILE,         // while
    DO,            // do
    FOR,           // for
    NULL,          // null
    FALSE,         // false
    TRUE,          // true
    READ,          // read
    RANDOM,        // random
    LENGTH,        // length
    KEYS,          // keys
    VALUES,        // values
    TOBOOL,        // tobool
    TOINT,         // toint
    TOSTR,         // tostr

    // OTHERS
    NAME,          // identifier
    NUMBER,        // integer
    TEXT           // string

};
