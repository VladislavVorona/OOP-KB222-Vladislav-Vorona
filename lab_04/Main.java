import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inpScn = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Оберіть фігуру:");
            System.out.println("T - Трикутник");
            System.out.println("Q - Чотирикутник");
            System.out.println("C - Коло");
            System.out.println("X - Вихід");
            System.out.print("Введіть команду: ");
            String cmd = inpScn.nextLine().toUpperCase();

            switch (cmd) {
                case "T":
                    startTriangle(inpScn);
                    break;
                case "Q":
                    startQuadrilateral(inpScn);
                    break;
                case "C":
                    startCircle(inpScn);
                    break;
                case "X":
                    exit = true;
                    break;
                default:
                    System.out.println("Невідома команда.");
                    break;
            }
        }
        inpScn.close();
    }

    private static void startTriangle(Scanner inpScn) {
        System.out.println("Введіть координати точок для Трикутника:");
        Point[] points = new Point[3];
        try {
            for (int xx = 0; xx < 3; xx++) {
                points[xx] = seekPoint(inpScn, "Введіть координати точки " + (char)('A' + xx) + ": ");
            }
            Figure xFigure = new Triangle(points[0], points[1], points[2]);
            drawFigur(xFigure);
        } catch (NullPointerException e) {
            System.out.println("Вас повернуто в меню вибору команд.");
        }
    }


    private static void startQuadrilateral(Scanner inpScn) {
        System.out.println("Введіть координати точок для Чотирикутника:");
        Point[] points = new Point[4];
        try {
            for (int xx = 0; xx < 4; xx++) {
                points[xx] = seekPoint(inpScn, "Введіть координати точки " + (char)('A' + xx) + ": ");
            }
            Figure xFigure = new Quadrilateral(points);
            drawFigur(xFigure);
        } catch (NullPointerException e) {
            System.out.println("Вас повернуто в меню вибору команд.");
        }
    }


    private static void startCircle(Scanner inpScn) {
        try {
            Point center = seekPoint(inpScn, "Введіть координати центру для Кола: ");
            double radius = checkInpt(inpScn, "Введіть радіус для Кола: ");
            Figure xFigure = new Circle(center, radius);
            drawFigur(xFigure);
        } catch (NullPointerException e) {
            System.out.println("Вас повернуто в меню вибору команд.");
        }
    }

    private static Point seekPoint(Scanner inpScn, String message) {
        System.out.println(message);
        double xx = checkInpt(inpScn, "Введіть координату x: ");
        double xy = checkInpt(inpScn, "Введіть координату y: ");
        return new Point(xx, xy);
    }

    private static double checkInpt(Scanner inpScn, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(inpScn.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Неправильне значення, введіть число.");
            }
        }
    }

    private static double gtMinX(Figure xFigure) {
        double minX = Double.MAX_VALUE;
        for (Point p : xFigure.gtPoints()) {
            if (p.originX < minX) {
                minX = p.originX;
            }
        }
        return minX;
    }

    private static double gtMinY(Figure xFigure) {
        double minY = Double.MAX_VALUE;
        for (Point p : xFigure.gtPoints()) {
            if (p.originY < minY) {
                minY = p.originY;
            }
        }
        return minY;
    }

    private static void shiftFigur(Figure xFigure) {
        double minX = gtMinX(xFigure);
        double minY = gtMinY(xFigure);

        if (minX < 0 || minY < 0) {
            double shiftX = Math.max(-minX, 0);
            double shiftY = Math.max(-minY, 0);

            for (Point p : xFigure.gtPoints()) {
                p.shiftX = p.originX + shiftX;
                p.shiftY = p.originY + shiftY;
            }
        }
    }

    private static void drawFigur(Figure xFigure) {
        shiftFigur(xFigure);
        System.out.println(xFigure.gtFName() + " створено: " + xFigure);
        System.out.println("Площа " + xFigure.gtFName() + ": " + xFigure.calcArea());
        Point centroid = calcCentroid(xFigure); 
        System.out.println("Центроїд " + xFigure.gtFName() + ": " + centroid.originX + ", " + centroid.originY);

        double scale = Math.max(400.0 / gtMaxX(xFigure), 1);

        double maxX = gtMaxX(xFigure);
        double minX = gtMinX(xFigure);
        double maxY = gtMaxY(xFigure);
        double minY = gtMinY(xFigure);

        int windowWidth = (int) ((maxX - minX) * scale) + 20;
        int windowHeight = (int) ((maxY - minY) * scale) + 20;

        if (minX < 0) {
            windowWidth += (int) (-minX * scale);
        }

        if (minY < 0) {
            windowHeight += (int) (-minY * scale);
        }

        Frame frame = new Frame("Візуалізація фігури");
        frame.setSize(windowWidth, windowHeight);
        frame.add(new graphBoard(xFigure, scale, centroid)); 
        frame.setVisible(true);
    }

    private static Point calcCentroid(Figure xFigure) {
        double totalX = 0, totalY = 0;
        for (Point p : xFigure.gtPoints()) {
            totalX += p.originX;
            totalY += p.originY;
        }
        double centroidX = totalX / xFigure.gtPoints().length;
        double centroidY = totalY / xFigure.gtPoints().length;
        return new Point(centroidX, centroidY);
    }

    private static double gtMaxX(Figure xFigure) {
        if (xFigure instanceof Triangle) {
            return Math.max(((Triangle) xFigure).A.originX, Math.max(((Triangle) xFigure).B.originX, ((Triangle) xFigure).C.originX));
        } else if (xFigure instanceof Quadrilateral) {
            return Math.max(((Quadrilateral) xFigure).points[0].originX, Math.max(((Quadrilateral) xFigure).points[1].originX, Math.max(((Quadrilateral) xFigure).points[2].originX, ((Quadrilateral) xFigure).points[3].originX)));
        } else if (xFigure instanceof Circle) {
            return ((Circle) xFigure).center.originX + ((Circle) xFigure).radius;
        }
        return 0;
    }

    private static double gtMaxY(Figure xFigure) {
        if (xFigure instanceof Triangle) {
            return Math.max(((Triangle) xFigure).A.originY, Math.max(((Triangle) xFigure).B.originY, ((Triangle) xFigure).C.originY));
        } else if (xFigure instanceof Quadrilateral) {
            return Math.max(((Quadrilateral) xFigure).points[0].originY, Math.max(((Quadrilateral) xFigure).points[1].originY, Math.max(((Quadrilateral) xFigure).points[2].originY, ((Quadrilateral) xFigure).points[3].originY)));
        } else if (xFigure instanceof Circle) {
            return ((Circle) xFigure).center.originY + ((Circle) xFigure).radius;
        }
        return 0;
    }
}

class graphBoard extends Panel {
    private Figure xFigure;
    private double scale;
    private Point centroid;

    graphBoard(Figure xFigure, double scale, Point centroid) {
        this.xFigure = xFigure;
        this.scale = scale;
        this.centroid = centroid;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph = (Graphics2D) g;

        xFigure.draw(graph, scale);

        graph.setColor(Color.BLACK);
        for (int xx = 0; xx < xFigure.gtPoints().length; xx++) {
            Point p = xFigure.gtPoints()[xx];
            graph.drawString(String.format("%c(%.2f, %.2f)", (char)('A' + xx), p.originX, p.originY), (int)(p.shiftX * scale), (int)(p.shiftY * scale));
        }

        graph.drawString("Площа: " + xFigure.calcArea(), 10, 20);

        graph.drawString("Центроїд: (" + String.format("%.2f", centroid.originX) + ", " + String.format("%.2f", centroid.originY) + ")", 10, 40);
    }
}
