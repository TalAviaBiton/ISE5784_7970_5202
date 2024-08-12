package miniProject1;

import geometries.Plane;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.WHITE;
import static java.awt.Color.gray;

public class tenItems {
    Scene scene4 = new Scene.SceneBuilder("Test scene")
            .setAmbientLight(new AmbientLight(new Color(WHITE), 0.15))
            .setBackground(new Color(135, 206, 250)).build();


    boolean bvh = false;

    /** Camera builder for the tests with triangles */
//    private final Camera.Builder cameraBuilder = Camera.getBuilder()
//            .setDirection(new Vector(0,0,-1) ,new Vector(0,1,0))
//            .setRayTracer(new SimpleRayTracer(scene4).setNumOfRays(1000));

    /** Camera builder of the tests */
    private final Camera.Builder camera     = Camera.getBuilder()
            .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
            .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene4).setNumOfRays(3000));

    /**
     * gets a center and a radius of bubble and create
     *
     * @param point  center of bubble
     * @param radius radius of bubble
     */
    void buildBubble(Point point, double radius) {
        scene4.geometries.add(
                new Sphere(point, radius)
                        .setEmission(new Color(255, 255, 255).scale(0.05))
                        .setMaterial(new Material()
                                .setkD(0.1)
                                .setkS(0.9)
                                .setShininess(300)
                                .setkT(0.8)
                        ).setBVH(bvh)
        );
    }
    /**
     * gets 3 centers and 2 radius and create a cloud from one big sphere
     * at the center and 2 smaller spheres on the sides
     *
     * @param p1     center of big sphere
     * @param p2     center of small sphere
     * @param p3     center of small sphere
     * @param rBig   radius of big sphere
     * @param rSmall radius of small sphere
     */
    void buildCloud(Point p1, Point p2, Point p3, double rBig, double rSmall) {
        scene4.geometries.add(
                new Sphere(p1, rBig)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.54).setkS(0.003).setShininess(100).setkT(0.15))
                        .setBVH(bvh),
                new Sphere(p2, rSmall)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.54).setkS(0.003).setShininess(100).setkT(0.15))
                        .setBVH(bvh),
                new Sphere(p3, rSmall)
                        .setEmission(new Color(gray)) //
                        .setMaterial(new Material().setkD(0.54).setkS(0.003).setShininess(100).setkT(0.15))
                        .setBVH(bvh)
        );

    }



@Test
public void pic(){
//    cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000).setVpSize(150, 150)
//            .setRayTracer(new SimpleRayTracer(scene4).setNumOfRays(1000));
    //------------------------------------bubbles----------------------------------------------------------------
//    buildBubble(new Point(-30, 40, 120), 20d);
//    buildBubble(new Point(-25, 38, 120), 2d);
//    buildBubble(new Point(-40, -40, 120), 2d);
//    buildBubble(new Point(50, -35, 120), 1.5d);
//    buildBubble(new Point(30, -40, 120), 1.7d);
    //-----------------------------------------clouds-------------------------------------------------------------
    buildCloud(new Point(-55, 20, -80), new Point(-62, 18, -80),
            new Point(-48, 18, -80), 8d, 6d);
    buildCloud(new Point(60, -10, 0), new Point(65, -11, 0),
            new Point(55, -11, 0), 5d, 3.5d);
    //----------------------------------------------general--------------------------------------------------------
    scene4.geometries.add(
            new Sphere(new Point(-2, 28, -50), 35d)
                    .setEmission(new Color(150, 0, 0)) //
                    .setMaterial(new Material().setkD(0.3).setkS(0.05).setShininess(100).setkT(0.3)).setBVH(bvh),
         //-------------------------------------------ground-----------------------------------------------------------
             new Plane(new Point(0, -55, -750), new Vector(0, 0.5, 0))
                  .setEmission(new Color(34, 70, 34))
                 .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100)).setBVH(bvh),
            // ---------------------------------------------sky-----------------------------------------------------
            new Plane(new Point(5, -80, -300), new Vector(5, -240, -20))
                    .setEmission(new Color(185, 266, 500).scale(0.35))//
                    .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(100)).setBVH(bvh));
    // ---------------------------------------lights--------------------------------------------------------------

    //the sunlight
    scene4.lights.add(new DirectionalLight(new Color(200, 300, 100).scale(0.8), new Vector(-1, -2, -8)));


// with soft shadow featre
    //light for balloon coloring
    scene4.lights.add(new SpotLight(new Color(600, 700, 400), new Point(-2, 28, -50), new Vector(0, 1, 0)).setSize(30));

    //inside basket light
//    scene4.lights.add(new PointLight(new Color(200, 100, 100), new Point(0, -40, -50)).setSize(40));

    //the second shadow for baloon
    scene4.lights.add(new PointLight(new Color(105, 100, 100).scale(0.5), new Point(10, 10, 10)).setkQ(0).setkL(0).setSize(45));

    //street light for pinwheel
    scene4.lights.add(new PointLight(new Color(10,10,10), new Point(-40,-20,0)).setSize(30));
    //th light for the baloon decoration
    scene4.lights.add(new PointLight(new Color(40,40,40), new Point(-20,0,1001)).setSize(45));



//     //without soft shadows
//     //light for balloon coloring
//     scene4.lights.add(new SpotLight(new Color(600, 700, 400),
//     new Point(-2, 28, -50),
//     new Vector(0, 1, 0))
//     .setSize(0));
//
//     //inside basket light
//     scene4.lights.add(new PointLight(new Color(200, 100, 100),
//     new Point(0, -40, -50))
//     .setSize(0));
//
//     //the second shadow for baloon
//     scene4.lights.add(new PointLight(new Color(105, 100, 100).scale(0.5),
//     new Point(10, 10, 10)).setkQ(0).setkL(0).setSize(0));
//
//     //street light for pinwheel
//     scene4.lights.add(new PointLight(new Color(10, 10, 10),
//     new Point(-40, -20, 0))
//     .setSize(0));
//     //th light for the baloon decoration
//     scene4.lights.add(new PointLight(new Color(40, 40, 40),
//     new Point(-20, 0, 1001))
//     .setSize(0));

    ImageWriter imageWriter = new ImageWriter("final,SoftShadows,9000,9000", 9000, 9000);
    camera
            .setImageWriter(imageWriter).build()
            .renderImage()
            .writeToImage();

}
}
