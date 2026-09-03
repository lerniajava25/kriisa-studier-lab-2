package org.example.shapes;

import org.example.math.Ray;
import org.example.math.Vector3;

public class Sphere implements Shape {
    private final Vector3 center;
    private final double radius;
    private final Vector3 color; // Sfärens färg

    public Sphere(Vector3 center, double radius, Vector3 color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Hit hit(Ray ray) {
        Vector3 oc = ray.origin().sub(center);
        double a = ray.dir().dotProduct(ray.dir());
        double b = 2.0 * oc.dotProduct(ray.dir());
        double c = oc.dotProduct(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return Hit.miss();
        }

        // Hitta den närmaste positiva träffpunkten
        double t = (-b - Math.sqrt(discriminant)) / (2.0 * a);
        if (t < 0.001) {
            t = (-b + Math.sqrt(discriminant)) / (2.0 * a);
        }

        if (t > 0.001) {
            return new Hit(true, t, color);
        }
        return Hit.miss();
    }
}
