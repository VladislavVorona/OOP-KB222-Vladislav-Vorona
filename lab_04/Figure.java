import java.awt.*;

abstract class Figure {
    abstract double calcArea();
    abstract Point centroid();
    abstract String gtFName();
    abstract void draw(Graphics2D g, double scale);
    abstract Point[] gtPoints(); 
}

class Point {
    double originX, originY;
    double shiftX, shiftY;

    Point(double originX, double originY) {
        this.originX = originX;
        this.originY = originY;
        this.shiftX = originX;
        this.shiftY = originY;
    }
}
