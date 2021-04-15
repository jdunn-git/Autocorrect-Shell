package autoshell.CheckStrategies;

import java.util.Arrays;

import autoshell.CheckableCommand.Checkable;

public class NoCheckStrategy implements CheckStrategy {

	@Override
	public void generateVariants(Checkable com) {
		com.setVariants(Arrays.asList(com.getName()));
	}

}
