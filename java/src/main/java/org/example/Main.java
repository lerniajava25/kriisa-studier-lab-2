package org.example;

import org.example.math.Ray;
import org.example.math.Vector3;
import org.example.shapes.Shape;
import org.example.shapes.Sphere;
import org.example.shapes.Triangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 800;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        List<Shape> scene = new ArrayList<>();

        scene.add(new Sphere(new Vector3(0, 0, -5), 1.5, new Vector3(1.0, 0.2, 0.2)));
        scene.add(new Sphere(new Vector3(2, 1, -6), 1.0, new Vector3(0.2, 0.2, 1.0)));
        scene.add(new Triangle(
                new Vector3(-2, -1.5, -4),
                new Vector3(2, -1.5, -4),
                new Vector3(0, -3, -7),
                new Vector3(0.2, 0.8, 0.2)
        ));

        Vector3 cameraOrigin = new Vector3(0, 0, 0);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                double px = (x - width / 2.0) / (width / 2.0);
                double py = -(y - height / 2.0) / (height / 2.0);

                Vector3 rayDir = new Vector3(px, py, -1).normalize();
                Ray ray = new Ray(cameraOrigin, rayDir);

                Shape.Hit closestHit = Shape.Hit.miss();

                for (Shape shape : scene) {
                    Shape.Hit hit = shape.hit(ray);
                    if (hit.isHit() && hit.t() < closestHit.t()) {
                        closestHit = hit;
                    }
                }

                if (closestHit.isHit()) {
                    int r = (int) (closestHit.color().x() * 255);
                    int g = (int) (closestHit.color().y() * 255);
                    int b = (int) (closestHit.color().z() * 255);
                    int rgb = (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, rgb);
                } else {
                    int rgb = (20 << 16) | (20 << 8) | 40;
                    image.setRGB(x, y, rgb);
                }
            }
        }

        File outputFile = new File("image.png");
        ImageIO.write(image, "PNG", outputFile);
        System.out.println("Rendering klar! Bild sparad till: " + outputFile.getAbsolutePath());
    }
}
