package ShellCommand;

import autoshell.CheckableCommand.CDCheckableCommand;
import autoshell.CheckableCommand.DockerCheckableCommand;
import autoshell.CheckableCommand.GenericCheckableCommand;
import autoshell.CheckableCommand.GitCheckableCommand;

public interface ShellCommandVisitor {

	public void visit(GenericCheckableCommand vCom);

	public void visit(GitCheckableCommand vCom);
	
	public void visit(DockerCheckableCommand vCom);

	public void visit(CDCheckableCommand vCom);
}
