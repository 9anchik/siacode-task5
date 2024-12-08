package Various1;

import java.awt.*;
public enum CellType {
    OBSTACLE(-1, Color.BLACK),
    ROAD(1, Color.GREEN),
    LAND(2, Color.ORANGE),
    SAND(3, Color.YELLOW),
    PATH(0, Color.MAGENTA);

    private final int weight;
    private final Color color;

    CellType(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public static CellType fromWeight(int weight) {
        for (CellType type : values()) {
            if (type.weight == weight) {
                return type;
            }
        }
        return ROAD;
    }
}
