package interpreter.expr;

import interpreter.value.Value;
import interpreter.value.ListValue;

import java.util.List;
import java.util.ArrayList;

import interpreter.util.Utils;

public class ForListItem extends ListItem {
    private Variable var;
    private Expr expr; // lista
    private ListItem item;

    public ForListItem(int line, Variable var, Expr expr, ListItem item) {
        super(line);
        this.var = var;
        this.expr = expr;
        this.item = item;
    }

    @Override
    public List<Value<?>> items() {
        // Fazer uma lista com a variavel var (eh uma lista)

        if(!(this.expr.expr() instanceof ListValue))
            Utils.abort(super.getLine());
        
        List<Value<?>> values = new ArrayList<Value<?>>();;
        ListValue lv = (ListValue) expr.expr();
        List<Value<?>> listValues = lv.value();

        for(Value<?> v : listValues) {
            var.setValue(v);
            values.add(this.item.items().get(0));
        }
        

        //return values;
        //throw new RuntimeException("metodo items da classe ForListItem");
        return values;
    }
    
}
