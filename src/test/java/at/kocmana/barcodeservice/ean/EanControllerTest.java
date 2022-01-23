package at.kocmana.barcodeservice.ean;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.toByteArray;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import at.kocmana.barcodeservice.configuration.MessageConverterConfiguration;
import at.kocmana.barcodeservice.ean.model.Ean13;
import at.kocmana.barcodeservice.ean.model.Ean8;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EanController.class)
@Import(MessageConverterConfiguration.class)
class EanControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EanService service;

  @Test
  void generateEan13_ShouldReturnOkWithImage() throws Exception {
    //given
    var barcode = "123456789012";
    var requestedBarcode = new Ean13(barcode);
    var image = readBufferedImageFromFile("test-ean13.jpg");
    when(service.generateEan13(requestedBarcode)).thenReturn(image);

    //when
    mockMvc.perform(get("/ean13/" + barcode))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG))
        .andExpect(content().bytes(toByteArray(image)));
  }

  @Test
  void generateEan13WithIllegalArgument_ShouldReturnBadRequest() throws Exception {
    //given
    var barcode = "hans";

    //when
    var result = mockMvc.perform(get("/ean13/" + barcode))
        .andExpect(status().isBadRequest())
        .andReturn().getResponse().getContentAsString();

    System.out.println(result);
  }


  @Test
  void generateEan8_ShouldReturnOkWithImage() throws Exception {
    //given
    var barcode = "1234567";
    var requestedBarcode = new Ean8(barcode);
    var image = readBufferedImageFromFile("test-ean8.jpg");
    when(service.generateEan8(requestedBarcode)).thenReturn(image);

    //when
    mockMvc.perform(get("/ean8/" + barcode))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG))
        .andExpect(content().bytes(toByteArray(image)));
  }

}
