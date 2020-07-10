package academy.devdojo.webflux.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String,Object> errorAttributesMap  = super.getErrorAttributes((WebRequest) request, options);
        Throwable throwable = getError((WebRequest) request);
        if(throwable instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) throwable;
            errorAttributesMap.put("message", ex.getMessage());
            errorAttributesMap.put("developerMessage", "A ResponseStatusException Happened");
        }
        return errorAttributesMap;
    }
}
