/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkpool;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import static networkpool.NetworkPool.scene;

/**
 *
 * @author Nafis Mustakin
 */
public class CueStick {
    Polygon cuestick;
    
    Group root; 
    double x = 25;
    double angle = 0; 
    double prevAngle = 0;
    public double initialVel;
    double velocity; 
    boolean angleFlag = true; 
    PowerBar bar;
    Rectangle slider;
   
    
    public CueStick(Group root)
    {
        this.root = root; 
        //cuestick = new ImageView("images/cue2.png");
        //cuestick.setFitHeight(5);
        //cuestick.setFitWidth(300);
        //cuestick.setX(0);
        //cuestick.setY(339);
        
        cuestick=new Polygon(0,0,0+x,0,0+x,-2.2,25+x,-3,26+x,-3,200+x,-4,202+x,-4.1,260+x,-5,260+x,5,202+x,4.1,
                202+x, -4.1,200+x,-4,200+x,4,26+x,3,26+x,-3,25+x,-3,25+x,3,0+x,2.2,0+x,0);

        Stop[] stops = new Stop[] { new Stop(0, Color.BURLYWOOD), new Stop(1, Color.DARKRED)};
        LinearGradient lg = new LinearGradient(.5,0,.5,.7,true, CycleMethod.REFLECT,stops);
        cuestick.setFill(lg);
        
        bar = new PowerBar(root);
        slider = bar.getSlider();
        //cuestick.setRotate(180);
            
        
        root.getChildren().addAll(cuestick);
        
    }
    
    
    void setCuePos( double X, double Y) /// co-ordinate of the cue ball
    {
        cuestick.setLayoutX(X); //-certain value depending on the size of the image
        cuestick.setLayoutY(Y);
    }
    
    public void swingCue(double pivotX, double pivotY)
    {
        /*
            scene.setOnMouseDragged(event -> {
                cuestick.setCursor(Cursor.CLOSED_HAND);
                angle = Math.toDegrees(Math.atan2((event.getSceneY() - pivotY), (event.getSceneX() - pivotX)));
                if (angle < 0) {
                    angle += 360;
                }
                cuestick.getTransforms().addAll(new Rotate(angle - prevAngle, Rotate.Z_AXIS));
                prevAngle = angle;
                //System.out.println(angle);
            });
        */
        
        scene.setOnKeyTyped((KeyEvent event) -> {
            if("a".equals(event.getCharacter()))
            {
                angle -= 0.25;
                //System.out.println("+");
            }
            
            if("s".equals(event.getCharacter()))
            {
                angle +=0.25;
                //System.out.println("-");
            }
            if("q".equals(event.getCharacter()))
            {
                angle -= 4;
                //System.out.println("+");
            }
            if("w".equals(event.getCharacter()))
            {
                angle += 4;
                //System.out.println("+");
            }
            
            if (angle<0) angle+= 360;
            angle = angle%360;
            cuestick.getTransforms().addAll(new Rotate(angle-prevAngle, Rotate.Z_AXIS));
            prevAngle = angle;
            
            //System.out.println(angle);
        });

    }
    
    public void RotateCue()
    {
        cuestick.getTransforms().addAll(new Rotate(angle-prevAngle, Rotate.Z_AXIS));
        prevAngle = angle;
    }
    
    public void moveCue (double pivotX, double pivotY)
    {
        cuestick.setLayoutX(pivotX + velocity*Math.cos(Math.toRadians(angle)));
        cuestick.setLayoutY(pivotY + velocity*Math.sin(Math.toRadians(angle)));   
    }
    
    
    public Polygon getCue(){ return cuestick;}
    
    
    public void setInitialVel(double vel) {initialVel = vel;}
    public double InitialVel(){return initialVel;}
    public double getInitialVel() {
        slider.setOnMouseDragged(event-> {
            if(event.getSceneY() < 545 && event.getSceneY()>183)
            {
                slider.setLayoutY(event.getSceneY());
                velocity = (event.getSceneY() -183)/10; //adjust max velocity here 
                System.out.println("Still here");
                //System.out.println(velocity);
            }
        });
        
        slider.setOnMouseReleased((MouseEvent event) -> {
            initialVel = velocity;
           // velocity = 0;
            System.out.println("initial "+initialVel);
            slider.setLayoutY(183);
        });
        
        System.out.println("current initial "+initialVel);
        
        return initialVel;
        
    }
    
   
    
    
    public double getVel() {return velocity;}
    public void setVel(double vel) {velocity = vel;}
    
    public double getAngle() {return angle;}
    public void setAngle(double angle){this.angle = angle;}
 
    
    
}
