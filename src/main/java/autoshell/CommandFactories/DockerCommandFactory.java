package autoshell.CommandFactories;

import ShellCommand.BuildableShellCommand;
import ShellCommand.ShellCommandDocker;

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
