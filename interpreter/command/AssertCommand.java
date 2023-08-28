package interpreter.command;

import interpreter.expr.Expr;

import interpreter.value.BoolValue;
import interpreter.value.Value;

import interpreter.util.Utils;

public class AssertCommand extends Command {
    private Expr expr;
    private Expr msg;

    public AssertCommand(int line, Expr expr, Expr msg) {
        super(line);
        this.expr = expr;
        this.msg = msg;
    }

    @Override
    public void execute() {
        Value<?> v = expr.expr();

        if(!(v instanceof BoolValue))
            Utils.abort(super.getLine(), "A condição deve ser booleana");
        
        if(!(((BoolValue) v).value())) {
            if(this.msg == null)
                System.out.println("not true");
            else {
                System.out.println(this.msg.expr().value());
            }
        }

        //throw new RuntimeException("metodo execute da classe AssertCommand");
    }
}
