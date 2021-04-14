package CheckableCommand;

import ShellCommand.ShellCommandVisitor;

public interface Visitable {

	public void accept(ShellCommandVisitor scVisitor);
}
