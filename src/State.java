public class State {
    int x;
    int y;
    Maze maze;
    String prev_direc;
    VisitedMap visitedMap;

    //constructor
    public State(int x, int y, Maze maze, VisitedMap visitedMap, String prev_direc) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.prev_direc = prev_direc;
        this.visitedMap = visitedMap;
    }
}
