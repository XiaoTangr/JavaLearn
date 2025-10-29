package cn.javat.javaLearn.Shapes;

// 图形接口
public interface Shape {
    double getArea();

    double getPerimeter();

    void translate(double dx, double dy);

    void showVertices();
}