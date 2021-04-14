package CommandFactories;

import ShellCommand.BuildableShellCommand;
import ShellCommand.ShellCommandGeneric;

public class GenericCommandFactory implements CommandFactory {

	String name;
	
	public GenericCommandFactory() {
	}
	
	@Override
	public BuildableShellCommand BuildCommand(String command) {
		// TODO Auto-generated method stub
		name = command;
		BuildableShellCommand com = new ShellCommandGeneric();
		com.BuildCommand(command);
		return com;
	}
}
	