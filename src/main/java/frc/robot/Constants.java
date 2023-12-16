package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class Constants{


public static SimMode simMode = SimMode.DESKTOP;

public enum SimMode {
                HARDWARE,
                DESKTOP_VISION,
                DESKTOP
}

    private static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(25);
    private static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(25);

    public static class kSwerve{
      
//TODO offsets


        public static class Offsets {

            public static double frontLeft = -Math.toDegrees(-0.24);
            public static double backLeft = -Math.toDegrees(0.54);
            public static double backRight = -Math.toDegrees(1.97);
            public static double frontRight = -Math.toDegrees(-1.2);
          }

        public static final double steeringEncoderPositionFactor = (2 * Math.PI); // radians
        public static final double steeringEncoderVelocityFactor = (2 * Math.PI) / 60.0; // rad/s
    
         public static final double steeringEncoderPositionPIDMinInput = 0; // radians
        public static final double steeringEncoderPositionPIDMaxInput = steeringEncoderPositionFactor; // radians
        

        public static final int MaxSpeedMetersPerSecond = 2;
        public static final int MaxTranslationX = 1;
        public static final int MaxTranslationY = 1;
        public static final int Maxrotation = 1;
        
        public static final int frontLeftDrive = 1;
        public static final int frontLeftSteer =5;
        public static final int backLeftDrive = 2;
        public static final int backLeftSteer = 6;
        public static final int backRightDrive = 4;
        public static final int backRightSteer = 8;
        public static final int frontRightDrive = 3;
        public static final int frontRightSteer = 7;
  
        public static int kFrontLeftDriveAbsoluteEncoderPort = 1;
        public static int kBackLeftDriveAbsoluteEncoderPort = 3;
        public static int kFrontRightDriveAbsoluteEncoderPort = 2;
        public static int kBackRightDriveAbsoluteEncoderPort = 4;

        public static final SwerveDriveKinematics DRIVE_KINEMATICS =
        new SwerveDriveKinematics(
                // Front left
                new Translation2d(Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0,
                Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
                // Front right
                new Translation2d(Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0,
                        -Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
                // Back left
                new Translation2d(-Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0,
                Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0),
                // Back right
                new Translation2d(-Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0,
                        -Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0)
        );
    }}