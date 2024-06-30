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
        public Camera build()  {
            if (camera.vUp == Vector.ZERO)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing vUp");
            if (camera.vTo == Vector.ZERO)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing vTo");
            if (camera.p0 == Point.ZERO)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing p0");
            if (camera.distance == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing distance");
            if (camera.height == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing height");
            if (camera.width == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing width");
            setDirection(camera.vTo,camera.vUp);
            return (Camera) camera;//.clone()
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
         * set the width and height of the view screen
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
     */
    private Camera() {
    }

    /**
     * a get method for vTo
     *
     * @return vTo
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * a get method for vUp
     *
     * @return vUp
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * a get method for p0
     *
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * a get method for vRight
     *
     * @return vRight
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * a get method for distance
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * a get method for height
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * a get method for width
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }


    /**
     * return a new object of the Builder class the mourner
     *
     * @return a new Builder
     */
    public static Camera.Builder getBuilder() {
        return new Builder();
    }

    /**
     * calculate the dot product of the two vectors
     *
     * @return result the calculation of the dot product-int
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc=vTo.scale(distance);
        double Rx=width/nX;
        double Ry=height/nY;
        double xj=(j-(nX-1)/2)*Rx;
        double yi=(i-(nY-1)/2)*Ry;
        Vector pij= (Vector) pc.add(vRight.scale(xj).add(vUp.scale(yi)));
        Vector uij=pij.subtract(p0);
        return new Ray(p0,uij);
    }


}
