package autoshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ShellCommand.BuildableShellCommand;
import autoshell.CheckableCommand.Visitable;
import autoshell.CommandFactories.CDCommandFactory;
import autoshell.CommandFactories.DockerCommandFactory;
import autoshell.CommandFactories.GenericCommandFactory;
import autoshell.CommandFactories.GitCommandFactory;

public class UserPrompt {

	static String prompt = "auto-sh> ";
		
	static GenericCommandFactory genericCommandFactory;
	static CDCommandFactory cdCommandFactory;
	static GitCommandFactory gitCommandFactory;
	static DockerCommandFactory dockerCommandFactory;
	
	static List<Visitable> knownCommands;
	
	public static int runShell() throws IOException {
		while (true) {
			System.out.print(prompt);
			
	        // Enter data using BufferReader
	        BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	  
	        // Reading data using readLine
	        String input = reader.readLine();
	        input = input.toLowerCase();
	  	        
	        if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
	        	break;
	        }
	        
	        AutoShellCommandVisitor visitor = new AutoShellCommandVisitor(input, input.split(" "));
	        // TODO: Improve factory usage. I don't like that I am applying them like this every time.
	        visitor.genericFactory = genericCommandFactory;
	        visitor.cdFactory = cdCommandFactory;
	        visitor.gitCommandFactory = gitCommandFactory;
	        visitor.dockerCommandFactory = dockerCommandFactory;
	        
	        
	        // This could potentially become an object that uses the Iterable pattern, if time allows.
	        for (Visitable com : knownCommands) {
	        	com.accept(visitor);
	        }
	        
	        // Fall-through for commands if no match was found
	        if (!visitor.executed) {
	        	BuildableShellCommand com = genericCommandFactory.BuildCommand(input);
				com.execute();
	        }

		}
		
		return 0;
	}
	
}
