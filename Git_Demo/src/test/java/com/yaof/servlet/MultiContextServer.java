package com.yaof.servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MultiContextServer {  
    public static void main(String[] args) throws Exception {  
        Server server = new Server(8080);  
        // http://localhost:8080/hello/kongxx  
        ServletContextHandler context1 = new ServletContextHandler(ServletContextHandler.SESSIONS);  
        context1.setContextPath("/hello");  
        context1.setResourceBase(".");  
        context1.setClassLoader(Thread.currentThread().getContextClassLoader());  
        context1.addServlet(new ServletHolder(new HelloServlet("Hello Kongxx!")), "/kongxx");  
        context1.addServlet(new ServletHolder(new GoodByeServlet("Goodbye Kongxx!")), "/Goodbye"); 
        context1.addServlet(new ServletHolder(new MyServlet()), "/MyServlet");
        // http://localhost:8080/goodbye/kongxx  
        ServletContextHandler context2 = new ServletContextHandler(ServletContextHandler.SESSIONS);  
        context2.setContextPath("/goodbye");  
        context2.setResourceBase(".");  
        context2.setClassLoader(Thread.currentThread().getContextClassLoader());  
        context2.addServlet(new ServletHolder(new GoodByeServlet("Goodbye kongxx!")), "/kongxx");  
        ContextHandlerCollection contexts = new ContextHandlerCollection();  
        contexts.setHandlers(new Handler[] { context1, context2 });  
        server.setHandler(contexts);  
        server.start();  
        server.join();  
    }  
}