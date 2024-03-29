package org.firstinspires.ftc.teamcode.team.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.team.DarienOpModeAuto;

@Autonomous
public class RedFrontAdv extends DarienOpModeAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        initCamera(false);
        initControls(true);

        int propPosition;

        waitForStart();

        propPosition = getPropPosition();
        telemetry.addData("Prop", propPosition);
        telemetry.update();
        autoRunMacro("ReadyToPickup");
        setClawPosition("leftClosed"); // makes sure that the purple pixel is picked up
        MoveY(29, 0.3); //centers on spike tile
            setArmPosition(400, 0.5); // extends the arm

//            sleep(500);
        while(arm.isBusy()){print ("arm pos", arm.getCurrentPosition());}
            setWristPosition("dropGround"); // extends the wrist
        waitForMotors();
        switch (propPosition) {
            case 1:
                AutoRotate(90, 0.3,-1); // turns to spike mark
                MoveY(2.5, 0.2);
                waitForMotors();
                autoRunMacro("dropPixel"); // places the purple pixel on the ground
                MoveY(-2.5, 0.2);
                waitForMotors();
                MoveX(17.5, 0.3);  // moves 1 tile right to be facing the backdrop
                autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                AutoRotate(-90, 0.3, 1);
                MoveY(-34, 0.3);
                pickUpWhite();
                MoveY(7, 0.3);
                waitForMotors();
                AutoRotate(-90, 0.3, 0);
                MoveY(20, 0.3);
                waitForMotors();
                MoveX(-2, 0.3);
                break;
            case 2:
                autoRunMacro("dropPixel"); // places the pixel
                MoveY(-3, 0.2);
                waitForMotors();
                MoveX(-20, 0.3); // strafe left: goes 1 tile towards the pixel piles
                    autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                AutoRotate(-90, 0.4 ,1);
                MoveY(-15, 0.1);
                pickUpWhite();
                MoveY(8, 0.1);
                waitForMotors();
                AutoRotate(-90, 0.3, 0);
                MoveX(-24, 0.3);
                waitForMotors();
                MoveY(24, 0.3); // moves in line with top case
                waitForMotors();
                AutoRotate(-90, 0.3, 0);

                break;
            case 3:
                AutoRotate(-90, 0.3,1); // turns to spike mark
                MoveY(3, 0.1);
                waitForMotors();
                autoRunMacro("dropPixel"); // places the pixel
                MoveY(-4, 0.1);
                autoRunMacro("ReadyToPickup"); // returns the wrist
                waitForMotors();
                MoveX(6, 0.3);
                waitForMotors();
                MoveY(-36, 0.3);
                pickUpWhite();
                MoveY(8, 0.1);
                waitForMotors();
                AutoRotate(-90, 0.3, 0);
                MoveX(-24, 0.3);
                waitForMotors();
                MoveY(24, 0.3); // moves in line with top case
                waitForMotors();
                AutoRotate(-90, 0.3, 0);
                break;
        }
        // AT THIS POINT, THE ROBOT SHOULD BE IN THE CENTER OF THE TILE.
        autoRunMacro("ReadyToPickup");
        setArmPosition(-10,0.1);
        print("second half","");
        MoveY(72,0.8); // moves past stage door towards backdrop
        waitForMotors();
            setClawPosition("closed"); // grabs yellow pixel
        setArmPosition(500, 0.3); // extends the arm a tiny bit
        while (arm.isBusy()) {}
        autoRunMacro("ReadyToDrop"); // extends the wrist
        print("pls no crash","");
        backDropPlace(false, propPosition);
        MoveY(-5, 0.3);
        setWristPosition("dropGround");
        switch (propPosition) {
            case 1:
                MoveX(-18, 0.3);
                waitForMotors();
                break;
            case 2:
                MoveX(-24, 0.3);
                waitForMotors();
                break;
            case 3:
                MoveX(-30, 0.3);
                waitForMotors();
                break;
        }
        MoveY(10, 0.05);
        waitForMotors();
    }
}
