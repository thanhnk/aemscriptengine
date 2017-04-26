package com.qantas.core.developer.task;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.scripting.api.AbstractSlingScriptEngine;

import javax.script.*;
import java.io.*;

/**
 * Created by thanh on 4/19/17.
 */
public class PythonScriptEngine extends AbstractSlingScriptEngine {

    protected PythonScriptEngine(ScriptEngineFactory scriptEngineFactory) {
        super(scriptEngineFactory);
    }

    @Reference
    private ResourceResolver resourceResolver;

    @Override
    public Object eval(Reader reader, ScriptContext scriptContext) throws ScriptException {
        Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
        SlingScriptHelper helper = (SlingScriptHelper) bindings.get(SlingBindings.SLING);
        final Writer writer = scriptContext.getWriter();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine pyEngine = mgr.getEngineByName("python");
        SlingHttpServletRequest request = helper.getRequest();
        Resource contentResource = request.getResource();
        ValueMap properties = contentResource.adaptTo(ValueMap.class);
        try {
            scriptContext.setWriter(writer);
            Resource dataResource = helper.getScript().getScriptResource();
            InputStream is = dataResource.adaptTo(InputStream.class);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            bindings.put("properties", properties);
            pyEngine.eval(br, scriptContext);
        } catch (Exception ex) {
            throw new ScriptException(ex);
        }

        return null;
    }
}
