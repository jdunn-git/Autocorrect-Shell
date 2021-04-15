package autoshell.CheckableCommand;

import autoshell.ShellCommand.ShellCommandVisitor;

public interface Visitable {

	public void accept(ShellCommandVisitor scVisitor);
}
