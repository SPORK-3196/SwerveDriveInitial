package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants;

public class Swerve extends SubsystemBase {
    

    public Module FL = new Module(constants.frontLeftSteer,
    constants.kFrontLeftDriveAbsoluteEncoderPort,
    0);
    public Module FR = new Module(constants.frontRightSteer,
    constants.kFrontRightDriveAbsoluteEncoderPort,
    0);
    public Module BL = new Module(constants.backLeftSteer,
    constants.kBackLeftDriveAbsoluteEncoderPort,
    0);
    public Module BR = new Module(constants.backRightSteer,
    constants.kBackRightDriveAbsoluteEncoderPort,
    0);

    


    private SwerveDrivePoseEstimator Pose;
    private ChassisSpeeds speeds;

    public Swerve(){

        Pose = new SwerveDrivePoseEstimator(constants.kinematics,
        gyroAngle(),
        new SwerveModulePosition[]{
            FL.getPosition(),
            FR.getPosition(),
            BL.getPosition(),
            BR.getPosition()
        },
        new Pose2d(4, 4, new Rotation2d()));
        speeds = new ChassisSpeeds();
    }

    public Rotation2d gyroAngle(){
        return new Rotation2d(0);
    }

    public void Drive(ChassisSpeeds dSpeeds){
        var targetStates = constants.kinematics.toSwerveModuleStates(dSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(targetStates, 1);

        setStates(targetStates);

        speeds = dSpeeds;
    }


    public Command teleDrive(
    DoubleSupplier translation,
    DoubleSupplier strafe,
    DoubleSupplier rotation){

        return this.run(
            () -> 
            Drive(toField(
                Joystickcontrol(
                translation.getAsDouble(),
                strafe.getAsDouble(),
                rotation.getAsDouble())
            ))

        );



    }

    private ChassisSpeeds Joystickcontrol(
        double x,
        double Y,
        double z
    ){
        if(Math.abs(x) <= constants.kDeadband) x = 0;
        if(Math.abs(Y) <= constants.kDeadband) Y = 0;
        if(Math.abs(z) <= constants.kDeadband) z = 0;


        x = Math.copySign(x*x, x);
        Y = Math.copySign(Y*Y, Y);
        z = Math.copySign(z*z, z);

        var transVelocety = VecBuilder.fill(x, Y);

        transVelocety = transVelocety.times(1);

        return new ChassisSpeeds(transVelocety.get(0, 0), transVelocety.get(1, 0), z);
        
    }

    private ChassisSpeeds toField(ChassisSpeeds speeds){
        return new ChassisSpeeds(speeds.vxMetersPerSecond, speeds.vyMetersPerSecond, speeds.omegaRadiansPerSecond);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("rotation Speed", speeds.omegaRadiansPerSecond);
        SmartDashboard.putNumber("translation Speed", speeds.vxMetersPerSecond);
        SmartDashboard.putNumber("strafe speeds", speeds.vyMetersPerSecond);
    }

    public Pose2d getPose(){
        return Pose.getEstimatedPosition();
    }

    public SwerveModuleState[] getStates(){
        return new SwerveModuleState[]{
            FL.getstate(),
            FR.getstate(),
            BL.getstate(),
            BR.getstate()
        };
    }

    public void setStates(SwerveModuleState[] state){
        FL.setState(state[0]);
        // FR.setState(state[1]);
        // BL.setState(state[2]);
        // BR.setState(state[3]);
    }



}
