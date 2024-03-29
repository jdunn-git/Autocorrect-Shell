package autoshell.CheckableCommand;

import java.util.Arrays;
import java.util.List;

import ShellCommand.ShellCommandVisitor;

public class CDCheckableCommand extends VisitableCheckableCommand {

	String[] args = {"cd"};
	List<String> variants;
	
	public CDCheckableCommand() {	
		variants = Arrays.asList("cd");
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
