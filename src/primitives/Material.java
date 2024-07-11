package primitives;

public class Material {

     //the coefficient for diffusive part
    public Double3 kD = Double3.ZERO;

    //the coefficient for specular part
    public Double3 kS = Double3.ZERO;
    //the shininess power in Phong model
    public int nShininess = 0;


        public Material setKd(Double3 kD){
            this.kD =kD;
            return this;
        }
        public Material setKs(Double3 kS){
            this.kS =kS;
            return this;
        }

        public Material setShininess(int nShininess){
            this.nShininess=nShininess;
            return this;
        }

}
