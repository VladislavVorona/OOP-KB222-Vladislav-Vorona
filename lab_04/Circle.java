import java.awt.*;

class Circle extends Figure {
    Point center;
    double radius;

    Circle(Point center, double radius) {
        if (radius <= 0) {
            System.out.println("Невірний радіус, введіть додатнє значення.");
            return;
        }
        this.center = center;
        this.radius = radius;
    }

    @Override
    Point[] gtPoints() {
        return new Point[]{center};
    }

    @Override
    double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    Point centroid() {
        return center;
    }

    @Override
    public String toString() {
        return String.format("Коло[(%.2f, %.2f) %.2f]", center.originX, center.originY, radius);
    }

    @Override
    String gtFName() {
        return "Коло";
    }

    @Override
    void draw(Graphics2D g, double scale) {
        int x = (int)(center.shiftX * scale);
        int y = (int)(center.shiftY * scale);
        int r = (int)(radius * scale); 
        g.setColor(Color.BLACK);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(new Color(255, 192, 203)); 
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(Color.BLUE);
        g.fillOval(x - 3, y - 3, 6, 6);
    }
}