package App.awsHandler;

import App.services.IbanService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;


public class LambdaMethodHandler {
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        // Extract the JSON from the "body" field
        String body = request.getBody();

        // Initialize the response object
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        response.setHeaders(Collections.singletonMap("Access-Control-Allow-Origin", "*"));
        response.setHeaders(Collections.singletonMap("Access-Control-Allow-Methods", "OPTIONS,POST"));
        response.setHeaders(Collections.singletonMap("Access-Control-Allow-Headers", "Content-Type"));


        // Parse the JSON using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert the JSON string to a Map
            Map<String, Object> bodyMap = objectMapper.readValue(body, Map.class);

            // Extract the "iban" field from the Map
            String iban = (String) bodyMap.get("iban");
            context.getLogger().log("Input: " + iban);

            IbanService ibanService = new IbanService();

            // Your existing logic for processing the input
            String result = ibanService.ibanService(iban);

            // Set the response status code and body
            response.setStatusCode(200);
            response.setBody(result);

        } catch (IOException e) {
            // Handle JSON parsing exception
            context.getLogger().log("Error parsing JSON: " + e.getMessage());
            response.setStatusCode(400);
            response.setBody("Error parsing JSON");
        }

        return response;
    }
}
