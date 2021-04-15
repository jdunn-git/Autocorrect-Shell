package autoshell.CheckStrategies;

import autoshell.CheckableCommand.Checkable;

public interface CheckStrategy {

	public void generateVariants(Checkable com);
}
