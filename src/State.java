public class State {
    int x;
    int y;
    Maze maze;
    String result;
    String prev_direc;
    VisitedMap visitedMap;
    public State(int x, int y, String result, Maze maze, VisitedMap visitedMap, String prev_direc) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.result = result;
        this.prev_direc = prev_direc;
        this.visitedMap = visitedMap;
    }
}
