package cn.javat.javaLearn.Shapes.Impl;


import cn.javat.javaLearn.Shapes.Shape;

public class Triangle implements Shape {
    private double[] x;  // x坐标数组
    private double[] y;  // y坐标数组
    public static final int VERTEX_COUNT = 3;

    // 构造函数：直接传入三个顶点的坐标
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        x = new double[]{x1, x2, x3};
        y = new double[]{y1, y2, y3};
    }

    @Override
    public double getArea() {
        // 鞋带公式计算面积
        return Math.abs((x[0] * (y[1] - y[2]) + x[1] * (y[2] - y[0]) + x[2] * (y[0] - y[1])) / 2.0);
    }

    @Override
    public double getPerimeter() {
        return distance(0, 1) + distance(1, 2) + distance(2, 0);
    }

    // 计算两点间距离（通过索引）
    private double distance(int i, int j) {
        double dx = x[i] - x[j];
        double dy = y[i] - y[j];
        return Math.sqrt(dx * dx + dy * dy);
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
        System.out.print("三角形顶点: ");
        for (int i = 0; i < VERTEX_COUNT; i++) {
            System.out.printf("(%.1f, %.1f) ", x[i], y[i]);
        }
        System.out.println();
    }
}