package Various1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JPanel {
    private final CellType[][] map;
    private final int rows;
    private final int cols;
    private Cell startCell;
    private Cell endCell;
    private List<Cell> path;
    private Integer pathCost;

    public GUI(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.map = new CellType[rows][cols];
        this.startCell = null;
        this.endCell = null;
        this.path = new ArrayList<>();
        this.pathCost = 0;

        resetMap();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = Math.min(getWidth() / cols, getHeight() / rows);
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (startCell == null) {
                        startCell = new Cell(x, y);
                        map[y][x] = CellType.ROAD;
                    } else if (endCell == null) {
                        endCell = new Cell(x, y);
                        map[y][x] = CellType.ROAD;
                    } else {
                        map[y][x] = CellType.OBSTACLE;
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    CellType currentType = map[y][x];
                    switch (currentType) {
                        case ROAD -> map[y][x] = CellType.LAND;
                        case LAND -> map[y][x] = CellType.SAND;
                        case SAND -> map[y][x] = CellType.ROAD;
                    }
                }
                repaint();
            }
        });
    }

    public void resetMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = CellType.LAND;
            }
        }
        startCell = null;
        endCell = null;
        path.clear();
        pathCost = null;
        repaint();
    }

    public void findPath() {
        if (startCell == null || endCell == null) {
            JOptionPane.showMessageDialog(this, "Выберите начальную и конечную клетки.");
            return;
        }

        pathCost = Dijkstra.dijkstra(map, startCell.x, startCell.y, endCell.x, endCell.y, path);
        if (pathCost != -1) {
            JOptionPane.showMessageDialog(this, "Длина минимального пути: " + pathCost);
        } else {
            JOptionPane.showMessageDialog(this, "Путь не найден.");
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth() / cols, getHeight() / rows);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                g.setColor(map[y][x].getColor());
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

        if (startCell != null) {
            g.setColor(Color.BLUE);
            g.fillRect(startCell.x * cellSize, startCell.y * cellSize, cellSize, cellSize);
        }
        if (endCell != null) {
            g.setColor(Color.RED);
            g.fillRect(endCell.x * cellSize, endCell.y * cellSize, cellSize, cellSize);
        }

        if (path != null) {
            g.setColor(Color.RED);
            for (Cell cell : path) {
                g.fillRect(cell.x * cellSize, cell.y * cellSize, cellSize, cellSize);
            }
        }
    }
}