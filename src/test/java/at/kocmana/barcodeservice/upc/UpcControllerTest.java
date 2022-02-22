package at.kocmana.barcodeservice.upc;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.toByteArray;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import at.kocmana.barcodeservice.configuration.MessageConverterConfiguration;
import at.kocmana.barcodeservice.upc.model.UpcA;
import at.kocmana.barcodeservice.upc.model.UpcE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UpcController.class)
@Import(MessageConverterConfiguration.class)
class UpcControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UpcService service;

  @Test
  void generateUpcA_ShouldReturnOkWithImage() throws Exception {
    //given
    var barcode = "12345678901";
    var requestedBarcode = new UpcA(barcode);
    var image = readBufferedImageFromFile("upc/test-upc-a.jpg");
    when(service.generateUpcA(requestedBarcode)).thenReturn(image);

    //when
    mockMvc.perform(get("/upca/" + barcode))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG))
        .andExpect(content().bytes(toByteArray(image)));
  }

  @Test
  void generateUpcAWithIllegalArgument_ShouldReturnBadRequest() throws Exception {
    //given
    var barcode = "hans";

    //when
    mockMvc.perform(get("/upca/" + barcode))
        .andExpect(status().isBadRequest())
        .andReturn().getResponse().getContentAsString();
  }

  @Test
  void generateUpcE_ShouldReturnOkWithImage() throws Exception {
    //given
    var barcode = "1234567";
    var requestedBarcode = new UpcE(barcode);
    var image = readBufferedImageFromFile("upc/test-upc-e.jpg");
    when(service.generateUpcE(requestedBarcode)).thenReturn(image);

    //when
    mockMvc.perform(get("/upce/" + barcode))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG))
        .andExpect(content().bytes(toByteArray(image)));
  }

}
