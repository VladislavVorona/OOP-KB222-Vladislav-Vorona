import java.awt.*;
import java.util.stream.Stream;

class Quadrilateral extends Figure {
    Point[] points;

    Quadrilateral(Point... points) {
        if (points.length != 4 || !validQuad(points)) {
            System.out.println("Чотирикутник вироджений, введіть вірні точки.");
            return;
        }
        this.points = points;
    }

    private boolean validQuad(Point[] points) {
        for (int xx = 0; xx < points.length - 1; xx++) {
            for (int xy = xx + 1; xy < points.length; xy++) {
                if (points[xx].originX == points[xy].originX && points[xx].originY == points[xy].originY) {
                    return false;
                }
            }
        }
        double area = 0;
        for (int xx = 0; xx < 4; xx++) {
            int nextxx = (xx + 1) % 4;
            area += (points[xx].originX * points[nextxx].originY - points[nextxx].originX * points[xx].originY);
        }
        return Math.abs(area) > 0;
    }

    @Override
    Point[] gtPoints() {
        return points;
    }

    @Override
    double calcArea() {
        double area = 0;
        for (int xx = 0; xx < 4; xx++) {
            int nextxx = (xx + 1) % 4;
            area += (points[xx].originX * points[nextxx].originY - points[nextxx].originX * points[xx].originY);
        }
        return Math.abs(area) / 2.0;
    }

    @Override
    Point centroid() {
        double centroidX = 0, centroidY = 0;
        for (Point p : points) {
            centroidX += p.shiftX;
            centroidY += p.shiftY;
        }
        centroidX /= 4.0;
        centroidY /= 4.0;
        return new Point(centroidX, centroidY);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Чотирикутник[");
        for (int xx = 0; xx < 4; xx++) {
            sb.append((char)('A' + xx)).append(String.format("(%.2f, %.2f) ", points[xx].originX, points[xx].originY));
        }
        sb.setLength(sb.length() - 1); 
        sb.append("]");
        return sb.toString();
    }

    @Override
    String gtFName() {
        return "Чотирикутник";
    }

    @Override
    void draw(Graphics2D g, double scale) {
        int[] xPoints = Stream.of(points).mapToInt(p -> (int)(p.shiftX * scale)).toArray();
        int[] yPoints = Stream.of(points).mapToInt(p -> (int)(p.shiftY * scale)).toArray();
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 4);
        g.setColor(new Color(255, 192, 203)); 
        g.fillPolygon(xPoints, yPoints, 4);
        for (Point p : points) {
            g.setColor(Color.RED);
            g.fillOval((int)(p.shiftX * scale) - 2, (int)(p.shiftY * scale) - 2, 4, 4);
        }
    Point centroid = centroid();
    g.setColor(Color.BLUE);
    g.fillOval((int)(centroid.shiftX * scale) - 3, (int)(centroid.shiftY * scale) - 3, 6, 6);
    }
}