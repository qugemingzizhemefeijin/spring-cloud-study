package com.tigerjoys.spring.cloud.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

/**
 * 自定义 zuul 发生回退时处理
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    /**
     * 调用时发生异常，执行该方法，并把异常传入参数 throwable
     *
     * @param throwable
     * @return
     */
    public ClientHttpResponse fallbackResponse(Throwable throwable) {
        //可以从请求上下文中获取请求对象.
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        System.out.println("执行回退时发生异常啦!!!");
        throwable.printStackTrace();
        ClientHttpResponse clientHttpResponse = new ClientHttpResponse() {
            public HttpHeaders getHeaders() {
                //设置响应头
                HttpHeaders httpHeaders = new HttpHeaders();
                return httpHeaders;
            }

            public InputStream getBody() throws IOException {
                //返回消息响应体 GBK 解决乱码
                return new ByteArrayInputStream((getRoute()+"发生错误啦").getBytes("GBK"));
            }

            public HttpStatus getStatusCode() throws IOException {
                // http 状态
                return HttpStatus.OK;
            }

            public int getRawStatusCode() throws IOException {
                // http 返回嘛
                return 200;
            }
            @Override
            public String getStatusText() throws IOException {
                //设置状态文本
                return "OK";
            }

            public void close() {

            }
        };

        return clientHttpResponse;
    }

    public String getRoute() {
        //获取路由,默认是 zuul.routes.consumer 当设置serviceId 时获取的是 serviceId
        //当 路由consumer转发失败时启动
        //return "consumer";
        return "microservice-provider-user";
    }

    /**
     * 回退时执行方法 在本示例spring cloud 版本中已经过时
     *
     * @return
     */
    public ClientHttpResponse fallbackResponse() {
        return null;
        /*return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                //设置响应头
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("errorHead", "发生错误啦");
                return httpHeaders;
            }
            @Override
            public InputStream getBody() throws IOException {
                //返回消息响应体
                return new ByteArrayInputStream("发生错误啦".getBytes());
            }
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // http 状态
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
                // http 返回嘛
                return 200;
            }
            @Override
            public String getStatusText() throws IOException {
                //设置状态文本
                return "OK";
            }
            @Override
            public void close() {
            }
        };*/
    }
}