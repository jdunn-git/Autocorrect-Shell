package autoshell_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ShellCommand.BuildableShellCommand;
import ShellCommand.ShellCommandGeneric;
import autoshell.UserPrompt;
import autoshell.CheckableCommand.*;
//import autoshell.CheckStrategies.*;
import autoshell.CommandFactories.*;
//import autoshell.ShellCommand.*;

public class AutoshellTest {

	private class TestCommand extends BuildableShellCommand {
		
		String name;
		boolean executed = false;
		
		@Override
		public void BuildCommand(String command) {
			name = command;
		}

		@Override
		public void execute() {
			executed = true;
		}
		
	}
	
	private class TestCommandFactory implements CommandFactory {

		@Override
		public BuildableShellCommand BuildCommand(String command) {
			BuildableShellCommand com = new ShellCommandGeneric();
			com.BuildCommand(command);
			return com;
		}
		
	}
	
	@Test
	public void testBasicVariantCheck() {
		
		GenericCheckableCommand testCommand = new GenericCheckableCommand("test command");
		String com = "test comand";

        assertFalse(testCommand.checkIfMatch(com));
		
		String variants = "test comand";
		testCommand.setVariants(Arrays.asList(variants));
		
        assertTrue(testCommand.checkIfMatch(com));
		
	}

	@Test
	public void testFactories() {
		
		// Test GenericCommandFactory
		GenericCommandFactory genericCommandFactory = new GenericCommandFactory();
		BuildableShellCommand genericCom = genericCommandFactory.BuildCommand("Test 1");
		List<String> genericProcessBuilderCommands = genericCom.builder.command();
		assertEquals(Arrays.asList("sh", "-c", "Test 1"), genericProcessBuilderCommands);
		
		// Test CDCommandFactory
		CDCommandFactory cdCommandFactory = new CDCommandFactory();
		BuildableShellCommand cdCom = cdCommandFactory.BuildCommand("Test 2");
		ProcessBuilder cdProcessBuilder = cdCom.builder;
		assertEquals(null, cdProcessBuilder);

		// Test GitCommandFactory
		GitCommandFactory gitCommandFactory = new GitCommandFactory();
		BuildableShellCommand gitCom = gitCommandFactory.BuildCommand("Test 3");
		List<String> gitProcessBuilderCommands = gitCom.builder.command();
		assertEquals(Arrays.asList("Test", "3"), gitProcessBuilderCommands);

		// Test DockerCommandFactory
		DockerCommandFactory dockerCommandFactory = new DockerCommandFactory();
		BuildableShellCommand dockercCom = dockerCommandFactory.BuildCommand("Test 4");
		List<String> dockerProcessBuilderCommands = dockercCom.builder.command();
		assertEquals(Arrays.asList("/usr/local/bin/docker", "Test", "4"), dockerProcessBuilderCommands);
		
	}

}
