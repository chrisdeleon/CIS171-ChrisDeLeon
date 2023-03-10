package controlaclock;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Chris de Leon 2/21/2023 Control a Clock
 */
public class ControlAClock extends Application {

    // ClockPane has to be instantiated outside of the start method in order for event handlers to function
    ClockPane clock = new ClockPane();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a clock and a labelA

        // creates horizontal box that will contain the two buttons
        HBox hBox = new HBox(5);

        // creates two buttons and adds the start/stop event handlers
        Button BtnStop = new Button("Stop");
        Button BtnStart = new Button("Start");

        // aligns the horizontal box to be in the center and adds the two buttons into itself
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(BtnStop, BtnStart);

        // Place clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clock);

        // adds the horizontal box to the bottom of the pane
        pane.setBottom(hBox);

        // adds event handlers to buttons
        BtnStop.setOnAction(new stopHandler());
        BtnStop.setOnAction(new startHandler());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Control a Clock"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    class stopHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            clock.stop();
        }
    }

    class startHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            clock.start();
        }
    }

    /**
     * The main method is only needed for IDEs with limited JavaFX support. It
     * is not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPane extends Pane {

    private int hour;
    private int minute;
    private int second;

    // Animation that causes the setCurrentTime method to fire off which, in effect, updates the clock hands' position
    private final Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(1000), e -> setCurrentTime()));

    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
        animation.setCycleCount(Timeline.INDEFINITE); // runs this animation forever
        animation.play(); // plays this animation
    }

    // stops the clock
    public void stop() {
        this.animation.pause();
        System.out.println("Clock stopped.");
    }

    // starts the clock
    public void start() {
        this.animation.play();
        System.out.println("Clock started.");
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set a new hour
     */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /**
     * Return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Set a new minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /**
     * Return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set a new second
     */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /**
     * Paint the clock
     */
    public void paintClock() {
        // Initialize clock parameters
        double clockRadius
                = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
        Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
        Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength
                * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength
                * Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength
                * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength
                * Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength
                * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength
                * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);
        getChildren().clear(); // Clear the pane
        getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }
}
