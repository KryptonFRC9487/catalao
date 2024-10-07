package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.POV;

public class IntakeCommand extends Command{
    
IntakeSubsystem intakeSubsystem;
  private final XboxController p2Controller;

    public IntakeCommand(
        IntakeSubsystem intakeSubsystem,
        XboxController p2Controller
    ) {
    this.intakeSubsystem = intakeSubsystem;
    this.p2Controller = p2Controller;

    addRequirements(intakeSubsystem);
    }

    public void IntakeCollect(){
        intakeSubsystem.setIntakeSpeed
        (0.1);
    }

    public void IntakeStop(){
        intakeSubsystem.setIntakeSpeed
        (0);
    }

    @Override
    public void execute(){
        if (
      p2Controller.getLeftTriggerAxis() != 0 || IntakeSubsystem.motorinit2 == true
    ) {
      intakeSubsystem.setIntakeSpeed(-0.2);
    } else if (p2Controller.getRightTriggerAxis() != 0) {
      if (!IntakeSubsystem.limitSwitch) {
        intakeSubsystem.setIntakeSpeed(0);
      } else {
        intakeSubsystem.setIntakeSpeed(0.2);
      }
    } else if (p2Controller.getRawButton(Buttons.LEFT_BUMPER)) {
      intakeSubsystem.setIntakeSpeed(-0.2);
    } else if (p2Controller.getRawButton(Buttons.BUTTON_X)) {
      intakeSubsystem.setIntakeSpeed(-0.25);
    } else if (p2Controller.getPOV() == POV.LEFT) {
      intakeSubsystem.setIntakeSpeed(0.3);
    } else {
      intakeSubsystem.setIntakeSpeed(0);
      // if (intakeSubsystem.DigitalInput() != 0) 
      // IntakeSubsystem.Note = true;
      // }
    }
    }
    
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
      }
}
