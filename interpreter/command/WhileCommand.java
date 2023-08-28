package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class WhileCommand extends Command {

    private Expr expr;
    private Command cmds;

    public WhileCommand(int line, Expr expr, Command cmds) {
        super(line);
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        while (true) {
            Value<?> v = expr.expr();
            if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if (!b)
                break;

            cmds.execute();
        }
    }
    
}
