// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {

    public static CANSparkMax intakemotor1 ,intakemotor2;

    public static boolean motorinit2;

    public static boolean limitSwitch;
  
    public IntakeSubsystem() {

        intakemotor1 =
         new CANSparkMax(Constants.MotorId.INTAKE_PORT,MotorType.kBrushless);

        intakemotor2 = 
         new CANSparkMax(Constants.MotorId.INTAKE2_PORT, MotorType.kBrushless);    
  }
  
  public void setIntakeSpeed(double speed) {
    intakemotor1.set(speed);
    intakemotor2.set(speed);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {

    //motorinit2 = (intakemotor1.getEncoder().getVelocity() > 1 );
  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
