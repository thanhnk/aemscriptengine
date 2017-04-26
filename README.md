# Qantas AEM Developer Task

This is a simple AEM based project, it contains a sample component 'python-component' that utilises a .py script for its rendering.
The objective of this task is to successfully render this component i.e. allow Python to be used as a scripting/templating engine within AEM.

## Modules

This project contains three modules:

* core: Java bundle containing all core functionality. This is where you will implement your solution to allow the rendering of Python scripts.
* ui.apps: contains the python component, you are not required to modify anything in this module.
* ui.content: contains sample content using the python component. NOTE. there are no template definitions within this project, to render the component you can access directly via: http://localhost:4502/content/qantas/developer/task/jcr:content/python-component.html

## Install jython library (required)
Make sure jython-standalon-2.7.0.jar has been added to java classpath before starting AEM Instance
Ex:
    Run following command in unix OS to add the lib to java classpath:
    export CLASSPATH=$CLASSPATH:/path/to/jython-standalon-2.7.0.jar

    Or for Mac OS: copy jython-standalon-2.7.0.jar tp /Library/Java/Extensions/


## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle
    
## Viewing Python component
    
As described the python component is being referenced via the following content resource. Once this content package has been installed you will be able to access the component via:

    http://localhost:4502/content/qantas/developer/task/jcr:content/python-component.html


