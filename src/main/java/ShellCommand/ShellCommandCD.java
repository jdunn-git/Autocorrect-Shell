package ShellCommand;

public class ShellCommandCD extends BuildableShellCommand {

	String name = "cd";
		
	String[] args;
		
	public ShellCommandCD() {
	}

	public void SetArgs(String[] args) {
		this.args = args;
	}
	
	@Override
	public void BuildCommand(String command) {
	}
	
	@Override
	public void execute() {
		String currentDir = WorkingDirectory.getInstance().getDirectory();
		String newDir = currentDir + "/" + this.args[1];
		WorkingDirectory.getInstance().setDirectory(newDir);
		
		//System.out.println(newDir);
	}
	
}
