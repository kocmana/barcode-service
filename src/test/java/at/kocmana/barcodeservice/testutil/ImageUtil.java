package at.kocmana.barcodeservice.testutil;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {

  private ImageUtil(){}

  public static byte[] toByteArray(BufferedImage image) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpg", byteArrayOutputStream);
    } catch (IOException exception) {
      fail(exception.getMessage());
    }
    return byteArrayOutputStream.toByteArray();
  }

  public static boolean compareImages(BufferedImage imageA, BufferedImage imageB) {
   if (imageA.getWidth() != imageB.getWidth() || imageA.getHeight() != imageB.getHeight()) {
      return false;
    }

    int width  = imageA.getWidth();
    int height = imageA.getHeight();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        if (imageA.getRGB(x, y) != imageB.getRGB(x, y)) {
          return false;
        }
      }
    }

    return true;
  }
}
