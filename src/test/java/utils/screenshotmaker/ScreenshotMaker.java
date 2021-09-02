package utils.screenshotmaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {

    public static File takeScreenshot(String directoryPath) {
        File directory = new File(directoryPath);
        String fileName = directoryPath + "/" + System.currentTimeMillis() + ".png";
        File file = new File(fileName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(screenSize.width*2, screenSize.height*2));
            ImageIO.write(image, "png", file);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}