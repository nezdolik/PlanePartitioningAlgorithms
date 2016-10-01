package base.twodim;

/**
 * Created by nezdolik on 24.09.16.
 */
public class Rectangle {

    private Point2D leftUp;
    private Point2D leftBottom;
    private Point2D rightUp;
    private Point2D rightBottom;

    private double height;
    private double width;

    public Rectangle(Point2D leftUp, Point2D rightBottom, Point2D leftBottom, Point2D rightUp) {
        this.leftUp = leftUp;
        this.rightBottom = rightBottom;
        this.leftBottom = leftBottom;
        this.rightUp = rightUp;
        this.height = Math.abs(leftUp.getY() - leftBottom.getY());
        this.width = Math.abs(rightBottom.getX() - leftBottom.getX());
    }

    public double area(){
        return height * width;
    }


    public Point2D getLeftUp() {
        return leftUp;
    }

    public Point2D getLeftBottom() {
        return leftBottom;
    }

    public Point2D getRightUp() {
        return rightUp;
    }

    public Point2D getRightBottom() {
        return rightBottom;
    }

    public double height(){
        return height;
    }

    public double width(){
        return width;
    }

    public Point2D middlePoint() {
        return new Point2D(getLeftBottom().getX() + (width / 2), getLeftBottom().getY() + (height / 2));
    }
}
