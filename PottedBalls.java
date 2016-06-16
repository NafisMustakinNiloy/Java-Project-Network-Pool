/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkpool;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 *
 * @author Nafis Mustakin
 */
public class PottedBalls {
    double solidBallsX;
    double solidBallsY;
    double stripeBallsX;
    double stripeBallsY;
    int solidNo, stripeNo;
    ImageView LeftSlab, RightSlab; 
    
    PottedBalls(Group root)
    {
        solidBallsX = 295;
        solidBallsY = 40;
        
        stripeBallsX = 710;
        stripeBallsY = 40;
        solidNo = 0;
        stripeNo = 0;
        
        LeftSlab = new ImageView("images/box.png");
        RightSlab = new ImageView("images/box.png");
        
        LeftSlab.setFitHeight(40);
        LeftSlab.setFitWidth(250);
        LeftSlab.setLayoutX(270);
        LeftSlab.setLayoutY(20);
        
        RightSlab.setFitHeight(40);
        RightSlab.setFitWidth(250);
        RightSlab.setLayoutX(685);
        RightSlab.setLayoutY(20);
        
        root.getChildren().addAll(LeftSlab, RightSlab);
        
    }
    
    public void potSolidBall(SphereBalls ball)
    {
        ball.setVx(0);
        ball.setVy(0);
        ball.ball.setLayoutX(solidBallsX);
        ball.ball.setLayoutY(solidBallsY);
        solidBallsX+= 30;
        solidNo++;
        
    }

     public void potStripeBall(SphereBalls ball)
    {
        ball.setVx(0);
        ball.setVy(0);
        ball.ball.setLayoutX(stripeBallsX);
        ball.ball.setLayoutY(stripeBallsY);
        stripeBallsX+= 30;
        stripeNo++;
        
    }
    
}
