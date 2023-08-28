package interpreter.command;

import java.util.List;

public class BlocksCommand extends Command {

    private List<Command> cmds;

    public BlocksCommand(int line, List<Command> cmds) {
        super(line);
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        for (Command c : cmds)
            c.execute();
    }
    
}
