package CSE464.Program9;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

@SuppressWarnings("restriction")
public class Tester3D extends Application {
    private static Sphere sphere, sphere2, sphere3, sphere4;
    private static PerspectiveCamera camera;
    final static double mouseSensitivity = .5;
    final static int N = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // locate random objects
        sphere = new Sphere( 150 );

        sphere.setTranslateX(1000);
        sphere.setTranslateY( 0 );
        sphere.setTranslateZ( 0 );

        sphere.setMaterial( new PhongMaterial( Color.RED ) );


        sphere2  = new Sphere( 150 );

        sphere2.setTranslateX( 0 );
        sphere2.setTranslateY( 0 );
        sphere2.setTranslateZ( 0 );

        sphere2.setMaterial( new PhongMaterial( Color.GREEN ) );

        sphere3  = new Sphere( 150 );

        sphere3.setTranslateX( 300 );
        sphere3.setTranslateY( 0 );
        sphere3.setTranslateZ( 1000 );

        sphere3.setMaterial( new PhongMaterial( Color.BLUE ) );


        sphere4  = new Sphere( 150 );

        sphere4.setTranslateX( -300 );
        sphere4.setTranslateY( 0 );
        sphere4.setTranslateZ( 0 );

        sphere4.setMaterial( new PhongMaterial( Color.YELLOW ) );

        // locate the camera
        camera = new PerspectiveCamera( true );
        camera.setTranslateX( 0 );
        camera.setTranslateY( 0 );
        camera.setTranslateZ( 0 );
        camera.setFieldOfView(30.0);
        camera.setFarClip(5000.0);
        camera.setNearClip(0.01);


        // create a font of light
        PointLight light = new PointLight();

        light.setTranslateX( 0 );
        light.setTranslateY( 0 );
        light.setTranslateZ( 0 );

        Group root = new Group( sphere, sphere2, sphere3, sphere4, light);
        Scene scene = new Scene( root, 1000, 700, true);

        scene.setFill(Color.WHITE);
        scene.setCamera(camera);
        scene.setOnMouseMoved(new MouseLook());
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                root.setTranslateZ(root.getTranslateZ()+10);
            } else if (e.getCode() == KeyCode.A) {
            	root.setTranslateX(root.getTranslateX()+10);
            } else if (e.getCode() == KeyCode.S) {
            	root.setTranslateZ(root.getTranslateZ()-10);
            } else if (e.getCode() == KeyCode.D) {
            	root.setTranslateX(root.getTranslateX()-10);
            } else if (e.getCode() == KeyCode.Q) {
            	root.setTranslateY(root.getTranslateY()+10);
            } else if (e.getCode() == KeyCode.E) {
            	root.setTranslateY(root.getTranslateY()-10);
            }
        });

        stage.setScene(scene);
        stage.setTitle("JavaFX Camera Movement Test");
        stage.show();
    }
    
    public Group genGroup() {
    	Group root = new Group();
    	
    	return root;
    }


    
    static class MouseLook implements EventHandler <MouseEvent> {
        private static Rotate rotation;
        private static int oldX, newX;
        private static int oldY, newY;
        private static boolean alreadyMoved = false;

        @Override
        public void handle(MouseEvent event) {
            if ( alreadyMoved ) {
                newX = (int) event.getScreenX();

                if ( oldX != newX ) {
                    rotation = new Rotate(mouseSensitivity*(newX-oldX),
                            camera.getTranslateX(), camera.getTranslateY(), camera.getTranslateZ(),
                            Rotate.Y_AXIS );


                }
                camera.getTransforms().addAll( rotation );

                oldX = newX;
                
                newY = (int) event.getScreenY();

                if ( oldY != newY ) {
                    rotation = new Rotate(mouseSensitivity*(oldY-newY),
                            camera.getTranslateX(), camera.getTranslateY(), camera.getTranslateZ(),
                            Rotate.X_AXIS );
                }
                camera.getTransforms().addAll( rotation );

                oldY = newY;
            } else {
                oldX = (int) event.getScreenX();
                oldY = (int) event.getScreenY();
                alreadyMoved = true;
            }
        }
    }
}