package CheckStrategies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CheckableCommand.Checkable;

public class SafeCheckStrategy implements CheckStrategy {

	String[] commandsToAvoid = {
			"git push",
			//"git status", // TODO: Just for testing - remove later
			"rm"
			};
	
	Map<String,List<String>> hardcodedVariantsMap = new HashMap<String,List<String>>(){{
		put("ls",Arrays.asList("ls", "sl"));
		put("pwd",Arrays.asList("pwd", "pdw"));
		put("git status",Arrays.asList("git status", "git stauts", "git sttaus", "gti status"));
	}};
	
	@Override
	public void generateVariants(Checkable com) {
		if (Arrays.asList(commandsToAvoid).contains(com.getName())) {
			com.setVariants(Arrays.asList(com.getName()));
		} 
		else if (hardcodedVariantsMap.containsKey(com.getName())) {
			com.setVariants(hardcodedVariantsMap.get(com.getName()));
		} 
		else {
			com.setVariants(Arrays.asList(com.getName()));
		}
	}

}
