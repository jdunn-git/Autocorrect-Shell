package CommandFactories;

import ShellCommand.BuildableShellCommand;

public interface CommandFactory {

	public BuildableShellCommand BuildCommand(String command);
}
