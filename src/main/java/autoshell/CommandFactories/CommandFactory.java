package autoshell.CommandFactories;

import autoshell.ShellCommand.BuildableShellCommand;

public interface CommandFactory {

	public BuildableShellCommand BuildCommand(String command);
}
