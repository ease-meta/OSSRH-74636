package builder;

import absfactory.MazeFactory;
import model.base.Maze;

/**
 * @Date 2016年7月29日19:49:01
 * @fun Builder
 * @author shadow
 *
 */
public abstract class MazeBuilder {
	public abstract void Buildmaze();
	public abstract void BuildRoom(int number);
	public abstract void BuildDoor(int roomFrom, int roomTo);
	public abstract Maze GetMaze();
	public abstract MazeFactory getMazeFactory();
	
	protected MazeBuilder(MazeFactory factory){
		this.factory = factory;
	}
	
	protected MazeFactory factory;
	protected Maze currentMaze;
}
