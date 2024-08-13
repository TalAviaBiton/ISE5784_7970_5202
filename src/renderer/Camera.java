package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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

    //the image writer for the scene
    private ImageWriter imageWriter;
    //the ray tracer for the camera
    private RayTracerBase rayTracer;

     // Amount of threads for multi threading, if not set is 0, so no multi threading is done
    private static int threads = 0;

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
        public Camera build() {
            if (camera.vUp == Vector.ZERO)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing vUp");

            if (camera.vTo == Vector.ZERO)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing vTo");

            if (camera.distance == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing distance");

            if (camera.height == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing height");

            if (camera.width == 0)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing width");

            if (camera.imageWriter == null)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing image writer");

            if (camera.rayTracer == null)
                throw new MissingResourceException("Missing rendering data", "Class Camera", "Missing rayTracerBase");

            setDirection(camera.vTo, camera.vUp);

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
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
         * @param width  the width of the view screen
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

        /**
         * set the image writer for the camera
         *
         * @param imageWriter the image writer of the camera
         * @return the camera, this object
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * set the ray tracer for the camera
         *
         * @param rayTracer the ray tracer of the camera
         * @return the camera, this object
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * set function for thread - builder design pattern
         *
         * @param threads sent threads to set
         * @return this camera that function was called from
         */
        public Builder setThreads(int threads) {
            Camera.threads = threads;
            return this;
        }

    }

    /**
     * Constructor to initialize vector based object with a point
     */
    public Camera() {
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
     * construct a ray throw  pixel
     *
     * @param nY for the resolution of the scene
     * @param nX for the resolution of the scene
     * @param j  the index of the pixel
     * @param i  the index of the pixel
     * @return the ray that goes throw the middle of the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = p0.add(vTo.scale(distance));
        double xJ = alignZero((j - (double) (nX - 1) / 2) * (width / nX));
        if (!isZero(xJ))
            pC = pC.add(vRight.scale(xJ));
        double yI = alignZero(-(i - (double) (nY - 1) / 2) * (height / nY));
        if (!isZero(yI))
            pC = pC.add(vUp.scale(yI));
        return new Ray(p0, pC.subtract(p0));
    }

    /**
     * delegate to write to image of image writer
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Image Writer is Missing", "Camera", "Set Image Writer");
        imageWriter.writeToImage();
    }

    /**
     * cast a ray throw a pixel and colors it
     *
     * @param nX     the resolution of the scene
     * @param nY     the resolution of the scene
     * @param i the x index of the pixel
     * @param j    the y index of the pixel
     */
    private Color castRay(int nX, int nY, int i, int j) {
        Ray ray = this.constructRay(nX, nY, i, j);
        Color color = this.rayTracer.traceRay(ray);
        this.imageWriter.writePixel(i, j, color);
        return color;
    }

    /**
     * a method that does the rendering of the image
     * @return the camera
     */
    public Camera renderImage() {
        int nY = imageWriter.getNy();
        int nX = imageWriter.getNx();
        //if not using multi threads
        if (threads < 1) {
            //goes through every pixel in view plane  and casts ray, meaning creates a ray for every pixel and sets the color
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {
                    this.castRay(nX, nY, i, j);
                }
            }
            return this;

        }
        //if using multi threads
        Pixel.initialize(nY, nX, 1);
        while (threads-- > 0) {
            new Thread(() ->
            {
                for (Pixel pixel = new Pixel();
                     pixel.nextPixel();
                     Pixel.pixelDone()) {
                    imageWriter.writePixel(pixel.col, pixel.row, castRay(nX, nY, pixel.row, pixel.col));
                }
            }).start();
        }
        Pixel.waitToFinish();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                this.castRay(nX, nY, i, j);
            }
        }
        return this;
    }

    /**
     * a method that prints the picture
     *
     * @param interval the distance of the pixel
     * @param color    the color of the grid
     * @return the image writer of the camera
     */
    public ImageWriter printGrid(int interval, Color color) {
//        for (int i = 0; i < imageWriter.getNx(); i++) {
//            for (int j = 0; j < imageWriter.getNy(); j++) {
//                if (i % interval == 0 || j % interval == 0) {
//                    imageWriter.writePixel(i, j, color);
//                }
//            }
//        }


        /*nested loop that goes through every pixel in grid and colors it*/
        for (int row = 0; row < imageWriter.getNy(); row++) {
            for (int column = 0; column < imageWriter.getNx(); column++) {
                /*for lines on net that are horizontal, for lines that are all net*/
                if ((row % (imageWriter.getNy() / 10) == 0) || ((row + 1) % (imageWriter.getNy() / 10) == 0))
                    imageWriter.writePixel(row, column, color);
                else
                    /*for vertical lines, since we are going through the pixels horizontally we will only reach 2 net
                     dots, every 50 pixels, as opposed to the whole line being net
                     */
                    if ((column % (imageWriter.getNx() / 10) == 0) || ((column + 1) % (imageWriter.getNx() / 10) == 0))
                        imageWriter.writePixel(row, column, color);
            }
        }
        return imageWriter;
    }
}
