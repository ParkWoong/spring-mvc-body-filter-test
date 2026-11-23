package io.github.parkwoong.example.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

@Component
@Order(1)
public class RequestHistoryLogger extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        chain.doFilter(new CachedRequestBodyWrapper(request), response);
    }
}

class CachedRequestBodyWrapper extends HttpServletRequestWrapper {

    private final byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public CachedRequestBodyWrapper(HttpServletRequest request) throws IOException {

        super(request);
        bytes = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        return new CachedServletInputStream(bytes);
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), "UTF-8"));
    }
}

class CachedServletInputStream extends ServletInputStream {

    private final InputStream cachedInputStream;

    public CachedServletInputStream(byte[] bytes) {

        cachedInputStream = new ByteArrayInputStream(bytes);
    }

    @Override
    public boolean isFinished() {

        try {
            return cachedInputStream.available() == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener arg0) {}

    @Override
    public int read() throws IOException {

        return cachedInputStream.read();
    }
}
