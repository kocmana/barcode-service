package at.kocmana.barcodeservice.ean;

import at.kocmana.barcodeservice.configuration.MessageConverterConfiguration;
import at.kocmana.barcodeservice.ean.model.Ean13;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(EanController.class)
@Import(MessageConverterConfiguration.class)
class EanControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EanService service;

  @Test
  void generateEan13_ShouldReturnNonNullResponse() throws Exception {
    //given
    var barcode = "123456789012";
    var requestedBarcode = new Ean13(barcode);
    var image = readBufferedImageFromFile("test-ean13.jpg");
    when(service.generateEan13(requestedBarcode)).thenReturn(image);

    //when
    mockMvc.perform(get("/ean13/" + barcode))
            .andExpect(status().isOk());
  }

  @Test
  void generateEan8() {
  }
}