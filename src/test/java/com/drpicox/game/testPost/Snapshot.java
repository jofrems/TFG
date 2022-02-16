package com.drpicox.game.testPost;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Snapshot {
    private final String method;
    private final String url;
    private final String requestBody;
    private int responseStatus;
    private String responseBody;
    private String handler;
    private String resolvedException;

    public Snapshot(String method, String url, String requestBody) {
        this.method = method;
        this.url = url;
        this.requestBody = requestBody;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponse(int responseStatus, String responseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }

    public String getPrettyPrint() {
        var message = new StringBuilder();
        message.append("Request Snapshot details:\n");
        message.append("-request             : ")
                .append(method)
                .append(" ")
                .append(url)
                .append("\n");
        message.append("-request body        : ").append(requestBody).append("\n");
        message.append("-response status     : ").append(responseStatus).append("\n");
        message.append("-response body       : ").append(responseBody).append("\n");
        message.append("-controller          : ").append(handler).append("\n");
        message.append("-controller exception: ").append(resolvedException).append("\n");
        return message.toString();
    }

    public void setHandler(Object handler) {
        this.handler = "" + handler;
    }

    public void setResolvedException(Exception resolvedException) {
        if (resolvedException == null) {
            this.resolvedException = "null";
            return;
        }

        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        resolvedException.printStackTrace(pw);

        this.resolvedException = resolvedException.getMessage() + "\n" + pw.toString();
    }
}
