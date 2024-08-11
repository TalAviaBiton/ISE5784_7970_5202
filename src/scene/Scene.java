package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

import lighting.*;
/**
 * a class that represent the scene
 */
public class Scene {

    //the name of the scene
    public String name;

    //the background of the scene with default color black
    public Color background = Color.BLACK;

    //the ambient light of the scene
    public AmbientLight ambientLight = AmbientLight.NONE;

    //the geometries in the scene
    public Geometries geometries = new Geometries();

    //the list of lights that are in the scene
    public List<LightSource> lights= new LinkedList<>();

    public double delta;
    public int softShadow;
    public boolean adaptiveSuperSampling;

    /**
     * a construct to the scene
     * @param name the name of the scene
     */
    public Scene(String name) {

        this.name = name;
    }

    /**
     * sets th geometries of the scene
     *
     * @param geometries the geometries in the scene
     * @return the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;

    }

    /**
     * sets th background of the scene
     *
     * @param background the color of the background of the scene
     * @return the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;

    }


    /**
     * sets th ambientLight of the scene
     *
     * @param ambientLight the color of the ambientLight of the scene
     * @return the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    //-----------------------------constructor-------------------------

    /**
     * Constructs a new Scene object using a SceneBuilder.
     *
     * @param builder the SceneBuilder to use for constructing the Scene object.
     */
    public Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;
        delta=builder.delta;
        softShadow=builder.softShadow;
        adaptiveSuperSampling=builder.adaptiveSuperSampling;

    }

    //--------------------------------getters----------------------------

    /**
     * Returns the name of the scene.
     *
     * @return the name of the scene.
     */
    public String getName() {
        return name;
    }


    //-------------------------------builder--------------------------------

    /**
     * A builder class for constructing Scene objects.
     */
    public static class SceneBuilder {

        private final String name;
        private List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = AmbientLight.NONE;
        private Geometries geometries = new Geometries();
        private double delta=0;
        private int softShadow=0;
        private boolean adaptiveSuperSampling;


        /**
         * Constructs a new SceneBuilder object with the given name.
         *
         * @param name the name of the scene.
         */
        public SceneBuilder(String name) {
            this.name = name;
        }
        /**
         * set the number of rays in soft shadow
         * @param rayNum number of rays in a row
         * @return scene
         */
        public SceneBuilder setSoftShadow(int rayNum) {
            softShadow=rayNum;
            return this;
        }
        public SceneBuilder setDelta(double d) {
            delta = d;
            return this;
        }
        /**
         * set if the scene use adaptive Super-sampling
         * @param a
         * @return scene
         */
        public SceneBuilder setAdaptiveSuperSampling(boolean a) {
            this.adaptiveSuperSampling=a;
            return this;
        }

        /**
         * Sets the background color of the scene and returns the updated SceneBuilder object.
         *
         * @param background the background color to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        /**
         * Sets the ambient light of the scene and returns the updated SceneBuilder object.
         *
         * @param ambientLight the ambient light to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * Sets the geometries in the scene and returns the updated SceneBuilder object.
         *
         * @param geometries the geometries to set.
         * @return the updated SceneBuilder object.
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * Builds a new Scene object using the current values of the SceneBuilder.
         *
         * @return a new Scene object.
         */
        public Scene build() {
            return new Scene(this);
        }

    }
}
