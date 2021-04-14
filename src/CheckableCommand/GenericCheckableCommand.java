package CheckableCommand;

import java.util.List;

import ShellCommand.ShellCommandVisitor;

public class GenericCheckableCommand extends VisitableCheckableCommand {

	public GenericCheckableCommand(String name) {
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
