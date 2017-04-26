package com.qantas.core.developer.task;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by thanh on 4/22/17.
 */
@SlingServlet(paths = "/bin/python", extensions = "py")
public class PythonServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine pyEngine = mgr.getEngineByName("python");
        String output;
        Writer writer = response.getWriter();
        writer.write("Start ----");
        try {
            output = String.valueOf(pyEngine.eval("print \"Python - Hello, world!\""));
            if (StringUtils.isBlank(output)){
                output = "Blank result";
            }
            writer.write(output);
        }
        catch (Exception ex) {
            output = "could not start Python" + ex;
            writer.write(output);
        }
        writer.write("End ----");
        writer.flush();
    }
}
