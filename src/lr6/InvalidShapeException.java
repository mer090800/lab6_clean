package lr6;

public class InvalidShapeException extends Exception {

    public InvalidShapeException(String shape) {
        super("Unsupported shape: " + shape +
                ". Available shapes: cube, cuboid, sphere, tetrahedron, torus, ellipsoid.");
    }
}
