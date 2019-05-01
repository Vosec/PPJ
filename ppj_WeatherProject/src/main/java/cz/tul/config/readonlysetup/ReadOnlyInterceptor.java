package cz.tul.config.readonlysetup;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ReadOnlyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if(!request.getMethod().equals("GET")) {
            //System.out.println("Pre Handle method is Calling");
            response.getWriter().write("Read only mode is active");
            return false;
        }
        return true;
    }

}
