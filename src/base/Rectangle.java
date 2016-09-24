package base;

/**
 * Created by nezdolik on 24.09.16.
 */
public class Rectangle {

    private Point leftUp;
    private Point leftBottom;
    private Point rightUp;
    private Point rightBottom;

    public Rectangle(Point leftUp, Point rightBottom, Point leftBottom, Point rightUp) {
        this.setLeftUp(leftUp);
        this.setRightBottom(rightBottom);
        this.setLeftBottom(leftBottom);
        this.setRightUp(rightUp);
    }


    public Point getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Point leftUp) {
        this.leftUp = leftUp;
    }

    public Point getLeftBottom() {
        return leftBottom;
    }

    public void setLeftBottom(Point leftBottom) {
        this.leftBottom = leftBottom;
    }

    public Point getRightUp() {
        return rightUp;
    }

    public void setRightUp(Point rightUp) {
        this.rightUp = rightUp;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(Point rightBottom) {
        this.rightBottom = rightBottom;
    }
}
