package lighting;
import primitives.*;

/**
 * interface to represent a light source
 */
public interface LightSource {
    /**
     * a method to get the intensity of a color in the point
     * @param point  th point i want to get th color of
     * @return th color of the point
     */
    public Color getIntensity(Point point);

    /**
     * @param point
     * @return
     */
    public Vector getL(Point point);

}
