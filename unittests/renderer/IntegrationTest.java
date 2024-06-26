package renderer;

import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    /** Camera builder for the tests */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            //         .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            //       .setImageWriter(new ImageWriter("Test", 1, 1))
            .setLocation(new Point(0,0,0.5))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1);

    Camera camera1 = cameraBuilder.setVpSize(6, 6).build();
    /**
     * Test method for
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testCameraSphere()
    {
        Sphere sphere1 = new Sphere(new Point(0,0,-3), 1);
        assertEquals(2
                ,findIntersectionWithGeometry(camera1, sphere1),
                "dddd");

        Sphere sphere2 = new Sphere(new Point(0,0,-2.5), 2.5);
        assertEquals(18
                ,findIntersectionWithGeometry(camera1, sphere2),
                "dddd");

        Sphere sphere3 = new Sphere(new Point(0,0,-2), 2);
        assertEquals(10
                ,findIntersectionWithGeometry(camera1, sphere3),
                "dddd");

        Sphere sphere4 = new Sphere(new Point(0,0,-1), 5);
        assertEquals(9
                ,findIntersectionWithGeometry(camera1, sphere4),
                "dddd");

        Sphere sphere5 = new Sphere(new Point(0,0,1), 0.5);
        assertEquals(0
                ,findIntersectionWithGeometry(camera1, sphere5),
                "dddd");
    }

    /**
     * Test method for
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testCameraPlane()
    {

    }

    /**
     * Test method for
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testCameraTriangle()
    {
        Triangle triangle1=new Triangle(
                new Point(1,-1,-2),
                new Point(0,1,-2),
                new Point(-1,-1,-2));
        assertEquals(1
                ,findIntersectionWithGeometry(camera1, triangle1),
                "dddd");

        Triangle triangle2=new Triangle(
                new Point(0,20,-2),
                new Point(1,-1,-2),
                new Point(-1,-1,-2));
        assertEquals(2
                ,findIntersectionWithGeometry(camera1, triangle2),
                "dddd");




    }

    public int findIntersectionWithGeometry(Camera camera, Geometry geometry)
    {
        int count=0;
        Ray ray;
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                ray=camera.constructRay(3, 3, j, i);
                count+=geometry.findIntersections(ray).size();
            }

        }

        return count;
    }

}
