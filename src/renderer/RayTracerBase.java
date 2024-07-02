package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {
    protected Scene scene;

    /** a constructor for ray tracer base
     * @param scene the scene of the photo
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * @param ray
     * @return
     */
    public abstract Color traceRay(Ray ray);

}
