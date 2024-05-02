import java.awt.*;

class Triangle extends Figure {
    Point A, B, C;

    Triangle(Point A, Point B, Point C) {
        if (!validTriangle(A, B, C)) {
            System.out.println("Неправильний трикутник, введіть вірні точки.");
            return;
        }

        this.A = A;
        this.B = B;
        this.C = C;
    }

    private boolean validTriangle(Point A, Point B, Point C) {
        if (A.originX == B.originX && A.originY == B.originY || A.originX == C.originX && A.originY == C.originY || B.originX == C.originX && B.originY == C.originY) {
            return false;
        }

        return (A.originX * (B.originY - C.originY) + B.originX * (C.originY - A.originY) + C.originX * (A.originY - B.originY) != 0);
    }

    @Override
    Point[] gtPoints() {
        return new Point[]{A, B, C};
    }

    @Override
    double calcArea() {
        return Math.abs((A.originX * (B.originY - C.originY) + B.originX * (C.originY - A.originY) + C.originX * (A.originY - B.originY)) / 2.0);
    }

    @Override
    Point centroid() {
        double centroidX = (A.shiftX + B.shiftX + C.shiftX) / 3.0;
        double centroidY = (A.shiftY + B.shiftY + C.shiftY) / 3.0;
        return new Point(centroidX, centroidY);
    }

    @Override
    public String toString() {
        return String.format("Трикутник[A(%.2f, %.2f) B(%.2f, %.2f) C(%.2f, %.2f)]", A.originX, A.originY, B.originX, B.originY, C.originX, C.originY);
    }

    @Override
    String gtFName() {
        return "Трикутник";
    }

    @Override
    void draw(Graphics2D g, double scale) {
        int[] xPoints = {(int)(A.shiftX * scale), (int)(B.shiftX * scale), (int)(C.shiftX * scale)};
        int[] yPoints = {(int)(A.shiftY * scale), (int)(B.shiftY * scale), (int)(C.shiftY * scale)};
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 3);
        g.setColor(new Color(255, 192, 203)); 
        g.fillPolygon(xPoints, yPoints, 3);
        g.setColor(Color.RED);
        for (Point p : new Point[]{A, B, C}) {
            g.fillOval((int)(p.shiftX * scale) - 2, (int)(p.shiftY * scale) - 2, 4, 4);
        }
        Point centroid = centroid();
        g.setColor(Color.BLUE);
        g.fillOval((int)(centroid.shiftX * scale) - 3, (int)(centroid.shiftY * scale) - 3, 6, 6);
    }
}