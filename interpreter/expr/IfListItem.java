package interpreter.expr;

import interpreter.value.BoolValue;
import interpreter.value.Value;

import interpreter.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class IfListItem extends ListItem {
    private Expr expr; // Condition
    private ListItem thenItem; // If stmt
    private ListItem elseItem; // Else stmt

    public IfListItem(int line, Expr expr, ListItem thenItem, ListItem elseItem) {
        super(line);
        this.expr = expr;
        this.thenItem = thenItem;
        this.elseItem = elseItem; // Null? PDF
    }

    @Override
    public List<Value<?>> items() {
        List<Value<?>> values = new ArrayList<Value<?>>();

        if(!(this.expr.expr() instanceof BoolValue))
            Utils.abort(super.getLine());

        if(((BoolValue) expr.expr()).value())
            return this.thenItem.items();
        if(this.elseItem != null)
            return this.elseItem.items();
        return values;
        //hrow new RuntimeException("metodo items da classe IfListItem");
    }
    
}
