package Various1;


public class Dijkstra {
    private static int INFINITY = 1000000;
        public static int dijkstra(CellType[][] grid, int startX, int startY, int endX, int endY) {
            int rows = grid.length;
            int cols = grid[0].length;

            int[][] dist = new int[rows][cols];
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    dist[i][j] = INFINITY;
                    visited[i][j] = false;
                }
            }

            dist[startX][startY] = 0;

            PriorityQueue<Cell> pq = new PriorityQueue<>();
            pq.add(new Cell(startX, startY), 0);

            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            while (!pq.isEmpty()) {
                Node<Cell> currentNode = pq.poll();
                Cell current = currentNode.getValue();
                int currentCost = currentNode.getPriority();

                int x = current.x;
                int y = current.y;

                if (visited[y][x]) {
                    continue;
                }
                visited[y][x] = true;

                if (x == endX && y == endY) {
                    return currentCost;
                }

                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];

                    if (newX >= 0 && newX < cols && newY >= 0 && newY < rows) {
                        if (grid[newY][newX] == CellType.OBSTACLE) {
                            continue;
                        }

                        int cellWeight = grid[newY][newX].getWeight();
                        int newCost = currentCost + cellWeight;

                        if (newCost < dist[newY][newX]) {
                            dist[newY][newX] = newCost;
                            pq.add(new Cell(newX, newY), newCost);
                        }
                    }
                }
            }
            return -1;
        }
    }
