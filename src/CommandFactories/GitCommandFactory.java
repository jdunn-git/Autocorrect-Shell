package CommandFactories;

import ShellCommand.BuildableShellCommand;
import ShellCommand.ShellCommandGit;

public class GitCommandFactory implements CommandFactory {

	String name;
	
	public GitCommandFactory() {
	}
	
	@Override
	public BuildableShellCommand BuildCommand(String command) {
		name = command;
		BuildableShellCommand com = new ShellCommandGit();
		com.BuildCommand(command);
		return com;
	}

}
