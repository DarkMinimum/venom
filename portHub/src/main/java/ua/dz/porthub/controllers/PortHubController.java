package ua.dz.porthub.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class PortHubController {

    private static final String REQUEST = "request";
    private static final String RESPONSE_CS = "response_c";
    private static final String RESPONSE_JAVA = "response_j";
    private static final String RESPONSE_PY = "response_p";

    private static final String PORT_CS = "1000";
    private static final String PORT_JAVA = "1001";
    private static final String PORT_PY = "1002";


    @GetMapping("/porthub/")
    public String mappingGet() {
        return "index";
    }

    @PostMapping("/porthub/")
    public String mappingPost(@RequestBody String rawEx, Model model) {
        rawEx = rawEx.replaceAll("\\+", "");
        var ex = rawEx.substring(rawEx.indexOf("=") + 1);
        String result = java.net.URLDecoder.decode(ex, StandardCharsets.UTF_8);

        model.addAttribute(REQUEST, result);
//        model.addAttribute(RESPONSE_CS, sendToVenomInstance(result, PORT_CS));
        model.addAttribute(RESPONSE_JAVA, sendToVenomInstance(result, PORT_JAVA));
        model.addAttribute(RESPONSE_PY, sendToVenomInstance(result, PORT_PY));
        return "index";
    }

    private String sendToVenomInstance(String ex, String port) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port);
        var map = new LinkedMultiValueMap();
        map.add("value", ex);

        HttpEntity<?> entity = new HttpEntity(map, headers);

        HttpEntity<String> response = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.POST,
            entity,
            String.class);

        return response.getBody();
    }
}
