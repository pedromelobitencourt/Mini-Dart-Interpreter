package interpreter.command;

import interpreter.value.Value;
import interpreter.value.BoolValue;

import interpreter.expr.Expr;

import interpreter.util.Utils;

public class DoWhileCommand extends Command {
    private Command cmds;
    private Expr expr;

    public DoWhileCommand(int line, Command cmds, Expr expr) {
        super(line);
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        cmds.execute(); // Executa, pelo menos, uma vez

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

        //throw new RuntimeException("metodo execute da classe DoWhileCommand");
    }
}
