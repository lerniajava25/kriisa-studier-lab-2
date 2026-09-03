package org.example.shapes;

import org.example.math.Ray;
import org.example.math.Vector3;

public class Triangle implements Shape {
    private final Vector3 p1, p2, p3;
    private final Vector3 color;

    public Triangle(Vector3 p1, Vector3 p2, Vector3 p3, Vector3 color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }

    @Override
    public Hit hit(Ray ray) {
        Vector3 edge1 = p2.sub(p1);
        Vector3 edge2 = p3.sub(p1);
        Vector3 h = ray.dir().crossProduct(edge2);
        double a = edge1.dotProduct(h);

        if (a > -0.00001 && a < 0.00001) return Hit.miss(); // Strålen är parallell

        double f = 1.0 / a;
        Vector3 s = ray.origin().sub(p1);
        double u = f * s.dotProduct(h);
        if (u < 0.0 || u > 1.0) return Hit.miss();

        Vector3 q = s.crossProduct(edge1);
        double v = f * ray.dir().dotProduct(q);
        if (v < 0.0 || u + v > 1.0) return Hit.miss();

        double t = f * edge2.dotProduct(q);
        if (t > 0.001) {
            return new Hit(true, t, color);
        }
        return Hit.miss();
    }
}
