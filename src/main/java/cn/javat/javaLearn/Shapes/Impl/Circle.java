package cn.javat.javaLearn.Shapes.Impl;


import cn.javat.javaLearn.Shapes.Shape;

// 圆类（用圆心坐标表示）
public class Circle implements Shape {
    private double centerX;
    private double centerY;
    private double radius;
    public static final double PI = Math.PI;

    public Circle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public void translate(double dx, double dy) {
        centerX += dx;
        centerY += dy;
    }

    @Override
    public void showVertices() {
        System.out.printf("圆圆心: (%.1f, %.1f), 半径: %.1f%n", centerX, centerY, radius);
    }
}