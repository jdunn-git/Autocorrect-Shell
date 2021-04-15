package autoshell;

import ShellCommand.BuildableShellCommand;
import ShellCommand.ShellCommandCD;
import ShellCommand.ShellCommandVisitor;
import autoshell.CheckableCommand.CDCheckableCommand;
import autoshell.CheckableCommand.DockerCheckableCommand;
import autoshell.CheckableCommand.GenericCheckableCommand;
import autoshell.CheckableCommand.GitCheckableCommand;
import autoshell.CommandFactories.CDCommandFactory;
import autoshell.CommandFactories.DockerCommandFactory;
import autoshell.CommandFactories.GenericCommandFactory;
import autoshell.CommandFactories.GitCommandFactory;

public class AutoShellCommandVisitor implements ShellCommandVisitor {

	String name;
	String[] args;
	
	boolean executed = false;
	boolean debug = false;
	
	GenericCommandFactory genericFactory;
	CDCommandFactory cdFactory;
	GitCommandFactory gitCommandFactory;
	DockerCommandFactory dockerCommandFactory;
	
	public AutoShellCommandVisitor(String name, String[] args) {
		this.name = name;
		this.args = args;
	}
	
	@Override
	public void visit(GenericCheckableCommand vCom) {
		if (!executed) {
			printCommand(vCom.getName());
			
			if (vCom.checkIfMatch(args[0])) {
				args[0] = vCom.getName();
				String combinedArgs = String.join(" ", args);
				BuildableShellCommand com = genericFactory.BuildCommand(combinedArgs);
				com.execute();
				executed = true;
			}
		}
	}

	@Override
	public void visit(GitCheckableCommand vCom) {
		if (!executed) {
			printCommand(vCom.getName());		
			
			if (args.length > 1) {
				name = args[0] + " " + args[1];
				
				if (vCom.checkIfMatch(name)) {
					args[1] = vCom.getArgs()[1];
					String combinedArgs = String.join(" ", args);
					BuildableShellCommand com = gitCommandFactory.BuildCommand(combinedArgs);
					com.execute();
					executed = true;
				}
			}
		}
	}
	
	@Override
	public void visit(DockerCheckableCommand vCom) {
		if (!executed) {
			printCommand(vCom.getName());
			
			if (args.length > 1) {
				name = args[0] + " " + args[1];

				if (vCom.checkIfMatch(name)) {
					args[1] = vCom.getArgs()[1];
					String combinedArgs = String.join(" ", args).substring(args[0].length()+1);
					BuildableShellCommand com = dockerCommandFactory.BuildCommand(combinedArgs);
					com.execute();
					executed = true;
				}
			}
		}
	}

	@Override
	public void visit(CDCheckableCommand vCom) {
		if (!executed) {
			printCommand(vCom.getName());
			
			if (vCom.checkIfMatch(args[0])) {
				args[0] = vCom.getName();
				String combinedArgs = String.join(" ", args);
				ShellCommandCD com = (ShellCommandCD)cdFactory.BuildCommand(combinedArgs);
				com.SetArgs(args);
				com.execute();
				executed = true;
			}
		}
	}

	// Used for debugging
	public void printCommand(String name) {
		if (debug) {
			System.out.println("Visiting command '" + name + "'");
		}
	}
}
