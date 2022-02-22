package at.kocmana.barcodeservice.qr;

import static at.kocmana.barcodeservice.testutil.FileUtil.readBufferedImageFromFile;
import static at.kocmana.barcodeservice.testutil.ImageUtil.toByteArray;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import at.kocmana.barcodeservice.configuration.MessageConverterConfiguration;
import at.kocmana.barcodeservice.qr.model.Qr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(QrController.class)
@Import(MessageConverterConfiguration.class)
class QrControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QrService service;

  @Test
  void generateQrCode_ShouldReturnOkWithImage() throws Exception {
    //given
    var payload = "some payload";
    var request = new Qr(payload);
    var requestedQrCode = readBufferedImageFromFile("qr/test-qr.jpg");
    when(service.generateQrCode(request)).thenReturn(requestedQrCode);

    //when
    mockMvc.perform(post("/qr")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"payload\": \"" + payload + "\"}"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.IMAGE_JPEG))
        .andExpect(content().bytes(toByteArray(requestedQrCode)));
  }

  @Test
  void generateQrCodeWithIllegalArgument_ShouldReturnBadRequest() throws Exception {
    //given
    var payload = "    ";

    //when
    mockMvc.perform(post("/qr")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"payload\": \"" + payload + "\"}"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Provided payload for QR generation is null or blank.")));
  }

}
