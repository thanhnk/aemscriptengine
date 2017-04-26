package com.qantas.core.developer.task;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
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

    private static final ScriptEngineManager mgr = new ScriptEngineManager();
    private static final ScriptEngine pyEngine = mgr.getEngineByName("python");

    @Override
    public Object eval(Reader reader, ScriptContext scriptContext) throws ScriptException {
        Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
        SlingScriptHelper helper = (SlingScriptHelper) bindings.get(SlingBindings.SLING);

        try {
            bindingGlobalObjects(bindings);
            final Writer writer = scriptContext.getWriter();
            scriptContext.setWriter(writer);
            Resource scriptResource = helper.getScript().getScriptResource();
            InputStream is = scriptResource.adaptTo(InputStream.class);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            pyEngine.eval(br, scriptContext);
        } catch (Exception ex) {
            throw new ScriptException(ex);
        }
        return null;
    }

    private void bindingGlobalObjects(final Bindings bindings){
        SlingScriptHelper helper = (SlingScriptHelper) bindings.get(SlingBindings.SLING);
        SlingHttpServletRequest request = helper.getRequest();
        Resource contentResource = request.getResource();
        ValueMap properties = contentResource.adaptTo(ValueMap.class);
        bindings.put("properties", properties);
        // Put other global objects if necessary
        // https://docs.adobe.com/docs/en/htl/docs/global-objects.html
    }
}
