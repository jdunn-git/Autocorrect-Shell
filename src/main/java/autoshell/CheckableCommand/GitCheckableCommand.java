package autoshell.CheckableCommand;

import java.util.Arrays;
import java.util.List;

import ShellCommand.ShellCommandVisitor;

public class GitCheckableCommand extends VisitableCheckableCommand {
	
	public GitCheckableCommand(String name) {
		name = "git " + name;
		args = name.split(" ");
		variants = Arrays.asList(name);
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
