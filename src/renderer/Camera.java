package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

/**
 * This class represents the camera in the scene
 */
public class Camera implements Cloneable {
    //the vector that gives the 'to' forward direction
    private Vector vTo;
    //the vector that gives the upward direction
    private Vector vUp;
    //the vector that gives the right direction
    private Vector vRight;

    //the point that the camera is on
    private Point p0;

    //the distance of the view screen
    private double distance = 0.0;
    //the height of the view screen
    private double height = 0.0;
    //the width of the view screen
    private double width = 0.0;

    /**
     * This class is builder class for camera
     */
    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * checking the parameters of camera
         *
         * @return a copy of the object camera
         */
        public Camera build() throws CloneNotSupportedException {
            if (camera.vUp == Vector.ZERO)
                throw new MissingResourceException("", "Class Camera", "");
            if (camera.vTo == Vector.ZERO)
                throw new MissingResourceException("", "Class Camera", "");
            if (camera.p0 == Point.ZERO)
                throw new MissingResourceException("", "Class Camera", "");
            if (camera.distance == 0)
                throw new MissingResourceException("", "Class Camera", "");
            if (camera.height == 0)
                throw new MissingResourceException("", "Class Camera", "");
            if (camera.width == 0)
                throw new MissingResourceException("", "Class Camera", "");
            setDirection(camera.vTo,camera.vUp);
            return (Camera) camera.clone();
        }

        /**
         * sets the location of the camera in the scene
         *
         * @param p0 the point that the camera is on
         * @return the camera,this object
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * set the direction of the camera
         *
         * @param vTo the vector that gives the 'to' forward direction
         * @param vUp the vector that gives the upward direction
         * @return the camera,this object
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (vTo.dotProduct(vUp) != 0)
                throw new IllegalArgumentException("vTo and vUp are not vertical");
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            camera.vRight = vTo.crossProduct(vUp).normalize();
            return this;
        }

        /**
         * set the dot width and height of the view screen
         *
         * @param width the width of the view screen
         * @param height the height of the view screen
         * @return the camera,this object
         */
        public Builder setVpSize(double width, double height) {
            camera.height = height;
            camera.width = width;
            return this;
        }

        /**
         * set the distance between the camera to the view screen
         *
         * @param distance the distance between the camera to the view screen
         * @return the camera,this object
         */
        public Builder setVpDistance(double distance) {
            camera.distance = distance;
            return this;
        }
    }

    /**
     * Constructor to initialize vector based object with a point
     *
     * @param xyz the point for the initialization
     */
    private Camera() {
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public Point getP0() {
        return p0;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public double getDistance() {
        return distance;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public double getHeight() {
        return height;
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public double getWidth() {
        return width;
    }


    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public static Camera.Builder getBuilder() {
        return new Builder();
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }


}
