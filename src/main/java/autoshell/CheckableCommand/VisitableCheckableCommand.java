package autoshell.CheckableCommand;

import java.util.List;

import autoshell.ShellCommand.ShellCommandVisitor;

public abstract class VisitableCheckableCommand implements Checkable, Visitable {

	String name;
	String[] args;
	
	List<String> variants;

	
	@Override
	public void accept(ShellCommandVisitor scVisitor) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String[] getArgs() {
		return args;
	}

	@Override
	public void setVariants(List<String> variants) {
		this.variants = variants;
	}

	@Override
	public boolean checkIfMatch(String str) {
		return variants.contains(str);
	}

}
