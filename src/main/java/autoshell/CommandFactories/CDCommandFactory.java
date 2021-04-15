package autoshell.CommandFactories;

import autoshell.ShellCommand.BuildableShellCommand;
import autoshell.ShellCommand.ShellCommandCD;

public class CDCommandFactory implements CommandFactory {

	String name = "cd";
	String[] args;
	
	public CDCommandFactory() {
	}
	
	public void SetArgs(String[] args) {
		this.args = args;
	}
	
	@Override
	public BuildableShellCommand BuildCommand(String command) {
		// TODO Auto-generated method stub
		BuildableShellCommand com = new ShellCommandCD();
		com.BuildCommand(command);
		return com;
	}
}
