package com.qantas.core.developer.task;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.scripting.api.AbstractScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

/**
 * Created by thanh on 4/26/17.
 */
@Component
@Service({ScriptEngineFactory.class})
public class PythonScriptEngineFactory extends AbstractScriptEngineFactory {
    public PythonScriptEngineFactory() {
        setExtensions("py");
        setMimeTypes("text/x-python");
        setNames("python");
    }

    @Override
    public String getLanguageName() {
        return "Python";
    }

    @Override
    public String getLanguageVersion() {
        return "2.7";
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new PythonScriptEngine(this);
    }
}
