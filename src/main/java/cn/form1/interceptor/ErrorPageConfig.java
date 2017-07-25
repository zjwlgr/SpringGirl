package cn.form1.interceptor;



import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


@Configuration
public class ErrorPageConfig implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(
                new ErrorPage(HttpStatus.BAD_REQUEST, "/4O0.html"),
                new ErrorPage(HttpStatus.UNAUTHORIZED, "/4O1.html"),
                new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html")
        );
    }
}
/*参考：http://www.imooc.com/article/16985
*
* http://blog.csdn.net/linzhiqiang0316/article/details/52600839*/

