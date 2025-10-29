package cn.javat.javaLearn.Shapes.Impl;


import cn.javat.javaLearn.Shapes.Shape;

// 矩形类
public class Rectangle implements Shape {
    private double[] x;
    private double[] y;
    public static final int VERTEX_COUNT = 4;

    // 构造函数：左上角坐标 + 宽 + 高
    public Rectangle(double left, double top, double width, double height) {
        x = new double[]{left, left + width, left + width, left};
        y = new double[]{top, top, top - height, top - height};
    }

    @Override
    public double getArea() {
        double width = x[1] - x[0];
        double height = y[0] - y[2];
        return width * height;
    }

    @Override
    public double getPerimeter() {
        double width = x[1] - x[0];
        double height = y[0] - y[2];
        return 2 * (width + height);
    }

    @Override
    public void translate(double dx, double dy) {
        for (int i = 0; i < VERTEX_COUNT; i++) {
            x[i] += dx;
            y[i] += dy;
        }
    }

    @Override
    public void showVertices() {
        System.out.print("矩形顶点: ");
        for (int i = 0; i < VERTEX_COUNT; i++) {
            System.out.printf("(%.1f, %.1f) ", x[i], y[i]);
        }
        System.out.println();
    }
}