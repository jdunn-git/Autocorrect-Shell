package autoshell.CommandFactories;

import autoshell.ShellCommand.BuildableShellCommand;
import autoshell.ShellCommand.ShellCommandDocker;

public class DockerCommandFactory implements CommandFactory {

	String name;
	
	public DockerCommandFactory() {
	}
	
	@Override
	public BuildableShellCommand BuildCommand(String command) {
		name = command;
		BuildableShellCommand com = new ShellCommandDocker();
		com.BuildCommand(command);
		return com;
	}
}
