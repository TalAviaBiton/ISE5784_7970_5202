package primitives;

/**
 * a class to represent the material the geometry is made of
 */
public class Material {

     //the coefficient for diffusive part
    public Double3 kD = Double3.ZERO;

    //the coefficient for specular part
    public Double3 kS = Double3.ZERO;
    //the shininess power in Phong model
    public int nShininess = 0;

    /**
     * a method to set the coefficient for diffusive part
     * @param kD the coefficient for diffusive part
     * @return the material the geometry is made of
     */
        public Material setKd(Double3 kD){
            this.kD =kD;
            return this;
        }

    /**
     * a method to set the coefficient for specular part
     * @param kS the coefficient for specular part
     * @return the material the geometry is made of
     */
    public Material setKs(Double3 kS){
            this.kS =kS;
            return this;
        }

    /**
     * a method to set the shininess power in Phong model
     * @param nShininess the shininess power in Phong model
     * @return the material the geometry is made of
     */
        public Material setShininess(int nShininess){
            this.nShininess=nShininess;
            return this;
        }

}
