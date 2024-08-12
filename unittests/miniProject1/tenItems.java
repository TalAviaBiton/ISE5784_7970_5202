package miniProject1;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import static java.awt.Color.*;
import static java.awt.Color.GREEN;

public class tenItems {
    Scene scene = new Scene.SceneBuilder("Test scene")
            .setAmbientLight(new AmbientLight(new Color(WHITE), 0.15))
            .setBackground(new Color(100, 200, 300)).build();


    boolean bvh = false;

    /** Camera builder for the tests with triangles */
//    private final Camera.Builder cameraBuilder = Camera.getBuilder()
//            .setDirection(new Vector(0,0,-1) ,new Vector(0,1,0))
//            .setRayTracer(new SimpleRayTracer(scene4).setNumOfRays(1000));

    /** Camera builder of the tests */
//    private final Camera.Builder camera     = Camera.getBuilder()
//            .setDirection(new Vector(0,0,-1), new Vector(0,1,0))
//            .setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
//            .setVpSize(200, 200)
//            .setRayTracer(new SimpleRayTracer(scene).setNumOfRays(400));


    // Updated Camera setup
    Camera.Builder camera = Camera.getBuilder()
            .setLocation(new Point(0, 0, 1000))  // Position the camera further back
            .setDirection(new Vector(0, 1, 0).normalize(), new Vector(0, 0, -1))  // Pointing towards the scene with correct up vector
            .setVpDistance(220)  // Adjusted to capture a larger view
            .setVpSize(200, 200)
            .setRayTracer(new SimpleRayTracer(scene).setNumOfRays(289));
    // Increased size for a broader view

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
        scene.geometries.add(
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
public void pic1(){

            scene.geometries.add(
                    new Plane(new Point(0, -600, 0), new Point(1, -600, 0), new Point(0, -600, 1)) //white
                            .setEmission(new Color(230, 230, 230)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
                    new Plane(new Point(-300, -600, -100), new Point(-290, -600, -600), new Point(-300, -500, -100)) //pink
                            .setEmission(new Color(PINK)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),

                    new Triangle (new Point (-150,-160,0),new Point (230,-160,0),new Point(40,-160,300))
                            .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkR(1).setkD(1).setkD(0.2).setShininess(10)),
                    new Triangle (new Point (-150,-160,0),new Point (230,-160,0),new Point(40,-400,110))
                            .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
                    new Triangle (new Point (-150,-160,0),new Point (40,-400,110),new Point(40,-160,300))
                            .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),
                    new Triangle (new Point (40,-400,110),new Point (230,-160,0),new Point(40,-160,300))
                            .setEmission(new Color(123, 63,0)).setMaterial(new Material().setkD(0.3).setkS(0.8).setShininess(30)),

                    //first floor-
                    new Sphere(new Point(-80,-100,0),60).setEmission(new Color(0,30,200))  .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//blue
                    new Sphere(new Point(160,-100,0),60).setEmission(new Color(300,0,200)) .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//pink
                    new Sphere(new Point(40,-100,0),60).setEmission(new Color(200,0,30)) .setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//red

                    new Sphere(new Point(-20,-100,110),60).setEmission(new Color(200,200,200)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)), //gray
                    new Sphere(new Point(100,-100,110),60).setEmission(new Color(100,12,200)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//purple

                    new Sphere(new Point(40,-100,220),60).setEmission(new Color(0,160,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//green

                    //second floar-
                    new Sphere(new Point(-20,0,55),60).setEmission(new Color(255,165,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//yellow
                    new Sphere(new Point(100,0,55),60).setEmission(new Color(255,170,180)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//pink
                    new Sphere(new Point(40,0,165),60).setEmission(new Color(255,100,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(60)),//orange

                    //third floar-
                    new Sphere(new Point(40,100,110),60).setEmission(new Color(255,30,0)).setMaterial(new Material().setkD(0.4).setkS(0.8).setShininess(40))//red
            );

            scene.geometries.add(new Sphere( new Point(30,-190,50),10).
                    setEmission(new Color(0,30,200))
                    .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)));

            //**************************************

            scene.lights.add(new DirectionalLight(new Color(50,50,50),new Vector(0.5,0.5,-0.5))); // from the top


        scene.lights.add(new SpotLight(new Color(400, 240, 0),new Point(100,1000,500),new Vector(-30,-30,0)).setSize(20)
                .setkL(1E-5).setkQ(1.5E-7));

        scene.lights.add(new SpotLight(new Color(400, 240, 0),new Point(40,50,200),new Vector(0,-1,0)).setSize(20)
                .setkL(1E-5).setkQ(1.5E-7));

        scene.lights.add(new SpotLight(new Color(200, 200, 200),new Point(-150,200,200),new Vector(-40,-40,-20)).setSize(13)
                .setkL(1E-5).setkQ(1.5E-7));

            scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-300,300,400),new Vector(40,-40,-20)).setSize(13)
                    .setkL(1E-5).setkQ(1.5E-7));

            scene.lights.add(new SpotLight(new Color(255, 255, 255),new Point(-20,0,250),new Vector(0,0,-1)).setSize(13)
                    .setkL(1E-5).setkQ(1.5E-7));

            scene.lights.add(new PointLight(new Color(180,180,180),new Point(-300,-300,250)).setSize(20));


    ImageWriter imagewriter=new ImageWriter("initial,SoftShadows,500,500",500,500);
    camera.setImageWriter(imagewriter).build()
            .renderImage()
            .writeToImage();

    }


//     scene.geometries.add(
//             new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
//                     .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(60)), //
//             new Sphere(new Point(0, 0, -50), 20d).setEmission(new Color(GREEN))
//                     .setMaterial(new Material().setkD(0.2).setkS(0.2).setShininess(100)));
//
//    // ---------------------------------------lights--------------------------------------------------------------
//
//    //the sunlight
//    scene.lights.add(new DirectionalLight(new Color(200, 300, 100).scale(0.8), new Vector(-1, -2, -8)));
//
//
//// with soft shadow featre
//    //light for balloon coloring
//    scene.lights.add(new SpotLight(new Color(600, 700, 400), new Point(-2, 28, -50), new Vector(0, 1, 0)).setSize(30));
//    //the second shadow for baloon
//    scene.lights.add(new PointLight(new Color(105, 100, 100).scale(0.5), new Point(10, 10, 10)).setkQ(0).setkL(0).setSize(45));
//    //street light for pinwheel
//    scene.lights.add(new PointLight(new Color(10,10,10), new Point(-40,-20,0)).setSize(30));
//    //th light for the baloon decoration
//    scene.lights.add(new PointLight(new Color(40,40,40), new Point(-20,0,1001)).setSize(45));



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


    @Test
    public void pic(){

        //-----------------------------------------clouds-------------------------------------------------------------
        buildCloud(new Point(-55, 20, -80), new Point(-62, 18, -80),
                new Point(-48, 18, -80), 8d, 6d);
        buildCloud(new Point(60, -10, 0), new Point(65, -11, 0),
                new Point(55, -11, 0), 5d, 3.5d);
        //----------------------------------------------general--------------------------------------------------------
        scene.geometries.add(
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
        scene.lights.add(new DirectionalLight(new Color(200, 300, 100).scale(0.8), new Vector(-1, -2, -8)));


// with soft shadow featre
        //light for balloon coloring
        scene.lights.add(new SpotLight(new Color(600, 700, 400), new Point(-2, 28, -50), new Vector(0, 1, 0)).setSize(30));

        //inside basket light
//    scene4.lights.add(new PointLight(new Color(200, 100, 100), new Point(0, -40, -50)).setSize(40));

        //the second shadow for baloon
        scene.lights.add(new PointLight(new Color(105, 100, 100).scale(0.5), new Point(10, 10, 10)).setkQ(0).setkL(0).setSize(45));

        //street light for pinwheel
        scene.lights.add(new PointLight(new Color(10,10,10), new Point(-40,-20,0)).setSize(30));
        //th light for the baloon decoration
        scene.lights.add(new PointLight(new Color(40,40,40), new Point(-20,0,1001)).setSize(45));



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
