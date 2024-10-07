// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Controle;
import frc.robot.Constants.Trajetoria;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.Teleop;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // Aqui iniciamos o swerve
  private SwerveSubsystem swerve = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  
  // Controle de Xbox, troque para o qual sua equipe estará utilizando
  private XboxController controleXbox = new XboxController(Controle.xboxControle);
  private XboxController CoControleXbox = new XboxController(Controle.CoXboxControle);

  private IntakeSubsystem Intake = new IntakeSubsystem();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Definimos o comando padrão como a tração
    swerve.setDefaultCommand(new Teleop(swerve, 
    // Aqui dentro temos vários inputs do nossos gamepad, estaremos passando a própria função pelo método,
    // apenas preferência de sintaxe, o desempenho em si não se altera
        () -> -MathUtil.applyDeadband(controleXbox.getLeftY(), Controle.DEADBAND), 
        () -> -MathUtil.applyDeadband(controleXbox.getLeftX(), Controle.DEADBAND), 
        () -> -MathUtil.applyDeadband(controleXbox.getRightX(), Controle.DEADBAND))
      );
    
    
  

    // Colocar os comandos definidos no PathPlanner 2024 da seguinte forma 
    NamedCommands.registerCommand("Intake", new PrintCommand("Intake"));

    // Configure the trigger bindings
    setDefaultCommands();
    configureBindings();
  }

  private void setDefaultCommands() {

  Intake.setDefaultCommand(
    new IntakeCommand(Intake, CoControleXbox)
  );

}

  // Função onde os eventos (triggers) são configurados
  private void configureBindings() {
      new JoystickButton(controleXbox, XboxController.Button.kA.value).onTrue(new InstantCommand(swerve::resetGyro));
  
      new JoystickButton(controleXbox, XboxController.Button.kB.value).toggleOnTrue(Commands.startEnd(swerve::resetHeading, swerve::disableHeading));

      //new JoystickButton(CoControleXbox, XboxController.Button.kLeftBumper.value).toggleOnTrue(Commands.startEnd(intakazinho::IntakeCollect, intakazinho::IntakeStop));
  }

  // Função que retorna o autônomo
  public Command getAutonomousCommand() {
    // Aqui retornamos o comando que está no selecionador
    return swerve.getAutonomousCommand(Trajetoria.NOME_TRAJETORIA, true);
  }

  // Define os motores como coast ou brake
  public void setMotorBrake(boolean brake) {
    swerve.setMotorBrake(brake);
  }
}
