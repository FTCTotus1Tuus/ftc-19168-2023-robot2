package org.firstinspires.ftc.teamcode.team.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.team.DarienOpModeAuto;

@Autonomous
public class BlueFrontOut extends DarienOpModeAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        initCamera(true);
        initControls(true);

        int propPosition;

        waitForStart();

        propPosition = getPropPosition();
        telemetry.addData("Prop", propPosition);
        telemetry.update();
        autoRunMacro("ReadyToPickup");
        setClawPosition("leftClosed"); // makes sure that the purple pixel is picked up
        if (propPosition!=2) {
            MoveY(28, 0.3); }//centers on spike tile
        else {MoveY(27, 0.3);}
            setArmPosition(300, 0.5); // extends the arm
        while(arm.isBusy()){print ("arm pos", arm.getCurrentPosition());}
            setWristPosition("dropGround"); // extends the wrist
        waitForMotors();
        switch (propPosition) {
            case 3:
                AutoRotate(-90, 0.3,1); // turns to spike mark
                MoveY(1, 0.1); // move toward the spike mark
                waitForMotors();
                autoRunMacro("dropPixel"); // places the purple pixel on the ground
                waitForMotors();
                MoveX(-19.5, 0.3);  // strafe left to center on the tile
                autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                AutoRotate(90, 0.3, -1);
                //MoveY(-0.5, 0.1); // back up a tad
                //waitForMotors();
                break;
            case 2:
                autoRunMacro("dropPixel"); // places the pixel
                MoveX(18, 0.3); // goes 1 tile towards the pixel piles
                    autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                AutoRotate(0, 0.1 ,1);
                MoveY(22, 0.3);
                waitForMotors();
                AutoRotate(90, 0.3, -1); // turns towards backdrop
                MoveY(16.5, 0.3); // moves in line with top case
                waitForMotors();
                break;
            case 1:
                AutoRotate(90, 0.3,-1); // turns to spike mark
                MoveY(2, 0.1);
                waitForMotors();
                autoRunMacro("dropPixel"); // places the pixel
                MoveY(-4.5, 0.1);
                autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                MoveX(19.5, 0.3); // moves in line with top case
                waitForMotors();
                break;
        }
        // AT THIS POINT, THE ROBOT SHOULD BE IN THE CENTER OF THE TILE IN FRONT OF THE STAGE DOOR.
        autoRunMacro("ReadyToPickup");
        setArmPosition(-10,0.1);
        AutoRotate(90, 0.3, 0);
        MoveY(72, 0.5); // moves past stage door towards backdrop
        waitForMotors();
            setClawPosition("closed"); // grabs yellow pixel
        sleep(200);
//        AutoRotate(90, 0.3, -1);
        setArmPosition(600, 0.3); // extends the arm a tiny bit
        while (arm.isBusy()) {}
        autoRunMacro("dropGround"); // extends the wrist
        print("pls no crash","");
        backDropPlace(true, propPosition);

        park(true, false, propPosition);
    }
}
