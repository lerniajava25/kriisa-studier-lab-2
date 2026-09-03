package org.example.shapes;

import org.example.math.Ray;
import org.example.math.Vector3;

public interface Shape {
    Hit hit(Ray ray);

    record Hit(boolean isHit, double t, Vector3 color) {
        public static Hit miss() {
            return new Hit(false, Double.MAX_VALUE, new Vector3(0, 0, 0));
        }
    }
}
