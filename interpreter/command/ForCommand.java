package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.Variable;

import interpreter.util.Utils;

import interpreter.value.ListValue;
import interpreter.value.Value;

import java.util.List;

public class ForCommand extends Command {
    private Variable var;
    private Expr expr;
    private Command cmds;

    public ForCommand(int line, Variable var, Expr expr, Command cmds) {
        super(line);
        this.var = var;
        this.cmds = cmds;
        this.expr = expr;
    }

    @Override
    public void execute() {
        if(!(this.expr.expr() instanceof ListValue))
            Utils.abort(super.getLine());

        ListValue lv = (ListValue) expr.expr();
        List<Value<?>> v = lv.value();
        //int length = v.size();

        for(Value<?> value : v) {
            var.setValue(value);
            cmds.execute();
        }

    }
    
}
