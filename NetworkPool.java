/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkpool;

import java.io.IOException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nafis Mustakin
 */
public class NetworkPool extends Application {
    
    
    public static SphereBalls[] balls = new SphereBalls[16];
    public static Group root = new Group();  
    public static Scene scene = new Scene(root, 1200,720);
  
    /*
    public void AllCollision()
    {
        for(int i =0; i<15; i++)
        {
            for(int j =i+1; j<16; j++)
            {
                balls[i].checkBallCollision(balls[j]);
                
            }
        }
        
        for(int i =0; i<16; i++)
        {
            balls[i].detectCollisionWithWall();
        }
       
    }
    */
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        primaryStage.setResizable(false);
        
        Parent Root = FXMLLoader.load(getClass().getResource("table.fxml"));
        
        root.getChildren().add(Root);
        
        
        GameConditions game = new GameConditions(root);
        Random rn =new Random();
        int i= rn.nextInt(2);
        System.out.println(i);

       
        //game.setTurn(true);
       
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.UpdateGameState(scene);
                game.setVelofCueBall();
                game.controlBallSpeed();
                game.Pocket();
                game.detectWallCollision();
               
                //ball.detectWallCollision2();
                game.BalltoBallCollision();
                game.Cue(scene);
                game.EndGame();
            }
        }.start();
         primaryStage.setTitle("Network Pool Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
        /*
        
        balls[0] = new SphereBalls(root, 820,358+13.5*2+1,"images/13.png" );
        balls[1] = new SphereBalls(root,820+(13.5+1)*1.732,358-13.5-1,"images/5.png");
        balls[2] = new SphereBalls(root,820-(13.5+1)*1.732,358-13.5-1,"images/7.png");
        balls[3] = new SphereBalls(root,820-(13.5+1)*2*1.732,358,"images/4.png");
        balls[4] = new SphereBalls(root,820+(13.5+1)*1.732,358+13.5*3+3,"images/2.png");
        balls[5] = new SphereBalls(root,820+(13.5+1)*2*1.732,358+(13.5+1)*2,"images/6.png");
        balls[6] = new SphereBalls(root,820+(13.5+1)*2*1.732,358-(13.5+1)*2,"images/3.png");
        balls[7] = new SphereBalls(root,820,358-(13.5+1)*2,"images/9.png");
        balls[8] = new SphereBalls(root,820+(13.5+1)*1.732,358+13.5+1,"images/15.png");
        balls[9] = new SphereBalls(root,820-(13.5+1)*1.732,358+13.5+1,"images/14.png");
        balls[10] = new SphereBalls(root,820+(13.5+1)*1.732,358-(13.5+1)*3,"images/12.png");
        balls[11] = new SphereBalls(root,820+(13.5+1)*2*1.732,358,"images/1.png");
        balls[12] = new SphereBalls(root,820+(13.5+1)*2*1.732,358-(13.5+1)*4,"images/10.png");
        balls[13] = new SphereBalls(root,820+(13.5+1)*2*1.732,358+(13.5+1)*4,"images/11.png");
        balls[14] = new SphereBalls(root,271,359,"images/cueball.png");
        balls[15] = new SphereBalls(root,820,358,"images/8.png");
        
        
        
        AnimationTimer newTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
               // ball1.moveBall(1); //To change body of generated methods, choose Tools | Templates.
               // ball2.moveBall(1);
                
                //for(int i =0; i<16 ; i++) balls[i].moveBall(1);
                //AllCollision();
                //System.out.println(ball1.getxPos()+ " "+ ball1.getyPos());
            }
        };
        
        newTimer.start();
        
        primaryStage.setTitle("Network Pool Game");
        primaryStage.setScene(scene);
        //scene.
        primaryStage.show();
        */
    
        
                
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
