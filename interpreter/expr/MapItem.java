package interpreter.expr;

public class MapItem {
    public Expr key;
    public Expr value;

    public MapItem(Expr key, Expr value) {
        this.key = key;
        this.value = value;
    }

    public Expr key() {
        return this.key;
    }

    public Expr value() {
        return this.value;
    }
}
