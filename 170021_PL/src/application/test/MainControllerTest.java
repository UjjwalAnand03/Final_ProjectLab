package application.test;

import application.Main;
import application.MainController;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainControllerTest {
@Test
   public void testOne() {

    Main.launch();
    MainController theMainControllerObject = new MainController();
    MainController.EBMSHubs ebmsHubs=theMainControllerObject.getCurrentlyCalledHub();
    assertTrue(ebmsHubs.equals(MainController.EBMSHubs.ADMIN_HUB));
}
}