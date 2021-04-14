package autoshell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import CheckableCommand.Visitable;
import CommandFactories.CDCommandFactory;
import CommandFactories.DockerCommandFactory;
import CommandFactories.GenericCommandFactory;
import CommandFactories.GitCommandFactory;

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
	  
	        // Printing the read line
	        //System.out.println("You entered: " + name);
	        
	        if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
	        	break;
	        }
	        
	        AutoShellCommandVisitor visitor = new AutoShellCommandVisitor(input, input.split(" "));
	        visitor.genericFactory = genericCommandFactory;
	        visitor.cdFactory = cdCommandFactory;
	        visitor.gitCommandFactory = gitCommandFactory;
	        visitor.dockerCommandFactory = dockerCommandFactory;
	        
	        
	        // TODO: Look for way to make this use the Iterable patterns
	        for (Visitable com : knownCommands) {
	        	com.accept(visitor);
	        }
	        
//	        if (!visitor.executed) {
//				BuildableShellCommand com = genericCommandFactory.BuildCommand(input);
//				com.execute();
//	        }

		}
		
		return 0;
	}
	
}
