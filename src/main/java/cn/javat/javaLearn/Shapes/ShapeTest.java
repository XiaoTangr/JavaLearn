package cn.javat.javaLearn.Shapes;


import cn.javat.javaLearn.Shapes.Impl.Circle;
import cn.javat.javaLearn.Shapes.Impl.Rectangle;
import cn.javat.javaLearn.Shapes.Impl.Triangle;

public class ShapeTest {
    public static void main(String[] args) {
        // 创建图形对象
        Shape triangle = new Triangle(0, 0, 3, 0, 0, 4);
        Shape rectangle = new Rectangle(1, 5, 4, 3);  // 左上角(1,5)，宽4，高3
        Shape circle = new Circle(2, 2, 2.5);

        // 初始状态
        System.out.println("=== 初始状态 ===");
        printInfo(triangle);
        printInfo(rectangle);
        printInfo(circle);

        // 平移操作
        double dx = 2, dy = 3;
        System.out.println("\n=== 平移(" + dx + "," + dy + ")后 ===");
        triangle.translate(dx, dy);
        rectangle.translate(dx, dy);
        circle.translate(dx, dy);

        printInfo(triangle);
        printInfo(rectangle);
        printInfo(circle);
    }

    // 静态工具方法：打印图形信息
    private static void printInfo(Shape shape) {
        shape.showVertices();
        System.out.printf("面积: %.2f, 周长: %.2f%n%n",
                shape.getArea(), shape.getPerimeter());
    }
}