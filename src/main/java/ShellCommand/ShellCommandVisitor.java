package ShellCommand;

import CheckableCommand.CDCheckableCommand;
import CheckableCommand.DockerCheckableCommand;
import CheckableCommand.GenericCheckableCommand;
import CheckableCommand.GitCheckableCommand;

public interface ShellCommandVisitor {

	public void visit(GenericCheckableCommand vCom);

	public void visit(GitCheckableCommand vCom);
	
	public void visit(DockerCheckableCommand vCom);

	public void visit(CDCheckableCommand vCom);
}
