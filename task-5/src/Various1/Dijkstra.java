package Various1;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    private static int INFINITY = 1000000;

    public static int dijkstra(CellType[][] grid, int startX, int startY, int endX, int endY, List<Cell> path) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        int[][] prev = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = INFINITY;
                visited[i][j] = false;
                prev[i][j] = -1;
            }
        }

        dist[startX][startY] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(c -> dist[c.y][c.x]));
        pq.add(new Cell(startX, startY));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell current = pq.poll();

            int x = current.x;
            int y = current.y;

            if (visited[y][x]) {
                continue;
            }
            visited[y][x] = true;

            if (x == endX && y == endY) {
                return Path(grid,prev,startX,startY,endX,endY,dist[y][x], path);
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                    if (grid[newY][newX] == CellType.OBSTACLE || visited[newY][newX]) {
                        continue;
                    }

                    int cellWeight = grid[newY][newX].getWeight();
                    int newCost = dist[y][x] + cellWeight;

                    if (newCost < dist[newY][newX]) {
                        dist[newY][newX] = newCost;
                        prev[newY][newX] = y * cols + x;
                        pq.add(new Cell(newX, newY));
                    }
                }
            }
        }
        return -1;
    }
    private static int Path(CellType[][] grid,int[][] prev, int startX, int startY, int endX, int endY, int cost, List<Cell> path) {
        int cols = grid[0].length;
        int x = endX;
        int y = endY;

        while (prev[y][x] != -1) {
            int prevX = prev[y][x] % cols;
            int prevY = prev[y][x] / cols;
            path.add(new Cell(prevX, prevY));
            grid[prevY][prevX] = CellType.PATH;
            x = prevX;
            y = prevY;
        }
        Collections.reverse(path);
        return cost;
    }
}
