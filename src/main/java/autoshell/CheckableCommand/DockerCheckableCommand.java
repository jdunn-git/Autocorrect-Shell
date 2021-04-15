package autoshell.CheckableCommand;

import java.util.List;

import autoshell.ShellCommand.ShellCommandVisitor;

public class DockerCheckableCommand extends VisitableCheckableCommand {
	
	public DockerCheckableCommand(String name) {
		name = "docker " + name;
		args = name.split(" ");
	}
	
	@Override
	public void accept(ShellCommandVisitor scVisitor) {
		scVisitor.visit(this);
	}

	@Override
	public String getName() {
		String name = String.join(" ", args);
		return name;
	}

	@Override
	public String[] getArgs() {
		return super.getArgs();	
	}

	@Override
	public void setVariants(List<String> variants) {
		super.setVariants(variants);
	}

	@Override
	public boolean checkIfMatch(String str) {
		return super.checkIfMatch(str);	
	}
}
