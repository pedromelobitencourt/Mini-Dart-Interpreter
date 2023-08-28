package interpreter.command;

import interpreter.value.Value;
import interpreter.value.BoolValue;
import interpreter.expr.Expr;
import interpreter.util.Utils;

public class IfCommand extends Command {

    private /*List<Command> */ Command cmdsIf;
    private /*List<Command> */ Command cmdsElse;
    private Expr expr;

    public IfCommand(int line, Expr expr, /*List<Command>*/ Command cmdsIf, /*List<Command>*/Command cmdsElse) {
        super(line);
        this.cmdsIf = cmdsIf;
        this.cmdsElse = cmdsElse;
        this.expr = expr;
    }

    @Override
    public void execute() {
        Value<?> v = expr.expr();

        if(!(v instanceof BoolValue))
            Utils.abort(super.getLine());
        
        BoolValue bv = (BoolValue) v;
        boolean b = bv.value();

        if(b){
            cmdsIf.execute();
        }
        else if(cmdsElse != null && !b) {
            cmdsElse.execute();
        }
    }
    
}
