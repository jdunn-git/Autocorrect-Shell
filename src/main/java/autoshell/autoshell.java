package autoshell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import autoshell.CheckStrategies.Context;
import autoshell.CheckStrategies.FullCheckStrategy;
import autoshell.CheckableCommand.CDCheckableCommand;
import autoshell.CheckableCommand.DockerCheckableCommand;
import autoshell.CheckableCommand.GenericCheckableCommand;
import autoshell.CheckableCommand.GitCheckableCommand;
import autoshell.CheckableCommand.Visitable;
import autoshell.CommandFactories.CDCommandFactory;
import autoshell.CommandFactories.DockerCommandFactory;
import autoshell.CommandFactories.GenericCommandFactory;
import autoshell.CommandFactories.GitCommandFactory;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class autoshell {
	
	public static String[] genericCommands = {"ls", "pwd", "ps"};
	public static String[] gitCommands = {"status", "pull", "commit", "add", "push"};
	public static String[] dockerCommands = {"ps", "image", "run", "logs"};
	
	public static void main(String args[]) {
		
//		System.out.println("Hello World!");	
		
		boolean isWindows = System.getProperty("os.name")
				  .toLowerCase().startsWith("windows");
		if (isWindows) {
			System.out.println("Unable to run on Windows");
			System.exit(0);
		}
		
		// TODO: Change what strategy is here based off args
//		Context context = new Context(new SafeCheckStrategy());
		Context context = new Context(new FullCheckStrategy());
		
		/*ShellCommand com = factory.BuildCommand("cd dev", null);
		com.RunCommand();
		com.PrintOutput();*/

		String dir = System.getProperty("user.dir");
		
		UserPrompt.genericCommandFactory = new GenericCommandFactory();
		UserPrompt.cdCommandFactory = new CDCommandFactory();
		UserPrompt.gitCommandFactory = new GitCommandFactory();
		UserPrompt.dockerCommandFactory = new DockerCommandFactory();
		
		List<Visitable> commands = new ArrayList<Visitable>();

		// Initialize Custom Commands	
		for (String str : genericCommands) {
			GenericCheckableCommand com = new GenericCheckableCommand(str);
			context.executeStrategy(com);
			commands.add(com);
		}
		for (String str : gitCommands) {
			GitCheckableCommand com = new GitCheckableCommand(str);
			context.executeStrategy(com);
			commands.add(com);
		}
		for (String str : dockerCommands) {
			DockerCheckableCommand com = new DockerCheckableCommand(str);
			context.executeStrategy(com);
			commands.add(com);
		}
		CDCheckableCommand com = new CDCheckableCommand();
		context.executeStrategy(com);
		commands.add(com);

		UserPrompt.knownCommands = commands;
		
		int results = 1;
		try {
			results = UserPrompt.runShell();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(results);
		
//		try {
	    /*
	    Scanner scan = new Scanner(System.in);
	    System.out.print("Type a command: ");
	    
	    
	    
	    String userCommand = scan.nextLine();
	    System.out.println("Your command: " + userCommand);

	    scan.close();
	    */
	}
}
