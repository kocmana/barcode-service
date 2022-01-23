package at.kocmana.barcodeservice.testutil;

import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.fail;

public class FileUtil {

  private static final String SRC = "src";
  private static final String TEST = "test";
  private static final String RESOURCES = "resources";

  private FileUtil() {
  }

  public static BufferedImage readBufferedImageFromFile(String filename) {
    BufferedImage image = null;
    try {
      var file = Paths.get(SRC, TEST, RESOURCES, filename).toFile();
      image = ImageIO.read(file);
    } catch (IOException exception) {
      fail(exception.getMessage());
    }
    requireNonNull(image);
    return image;
  }

}
