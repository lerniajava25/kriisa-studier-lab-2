package org.example.math;

public record Vector3(double x, double y, double z) {

    public Vector3 add(Vector3 b) {
        return new Vector3(this.x + b.x, this.y + b.y, this.z + b.z);
    }

    public Vector3 sub(Vector3 b) {
        return new Vector3(this.x - b.x, this.y - b.y, this.z - b.z);
    }

    public Vector3 multiply(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public double dotProduct(Vector3 b) {
        return this.x * b.x + this.y * b.y + this.z * b.z;
    }

    public Vector3 crossProduct(Vector3 b) {
        return new Vector3(
            this.y * b.z - this.z * b.y,
            this.z * b.x - this.x * b.z,
            this.x * b.y - this.y * b.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double len = length();
        if (len == 0) return this;
        return new Vector3(x / len, y / len, z / len);
    }
}
