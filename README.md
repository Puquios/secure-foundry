# Example Cloud Foundry pipeline for helloworld Java application using WebSphere Liberty  

Press this button, to get your own copy of the sample running in Bluemix !

[![Deploy To Bluemix](https://bluemix.net/deploy/button.png)](https://hub.jazz.net/deploy/index.html?repository=https://github.com/Puquios/secure-foundry.git)

## Overview 
IBM DevOps Services has a Continuous Delivery Pipeline for deploying Cloud Foundry applications, containers, and micro-services to IBM Bluemix. You can use a textual representation of a pipeline defined by a pipeline.yml file, which makes it easy to share and copy interesting pipelines. The Deploy to Bluemix button provides a simple way to clone a project that includes the source files and the Delivery Pipeline configuration. 

## The application 
Very simple java application based upon the Bluemix sample WordCounter sample app https://hub.jazz.net/project/pskhadke/WordCounter/overview that runs within a Liberty application server.  

A Dockerfile has been added to package application as a Container.  The application can be deployed as either a Cloud Foundry application or a Container on Bluemix.  

ShowResult.java has been modified so that it has a two common security issues.  This allows the application to be used to demonstrate static code scan capabilities.  

To say hello: http://myroute.mydomain?name=myName
To say inject a security issue: http://myroute.mydomain?name=<img src=x onerror=alert("ha") />

## The pipeline 
An interesting pipeline that demonstrates a few more advanced delivery pipeline capabilties 

- Package Application
    + Basic ant build to package a war file that includes best available translation from the Globalization project 
- Security Scanning
    + Leverages code scan security services to inspect war archive for security vunerabilities, provides a link to a dashboard of versioned security reports that map to the versioned application archives.  
- Deploy Stage
    + Deploys the war as a Cloud Foundry application to Bluemix 

The stages are setup with slack notifications.  By simply providing a slack WebHook in the stage configuration files you can recieve notifications of just job failures, or all activity in the pipeline.  

## References 
- [Blog on continuous delivery for containers](https://developer.ibm.com/bluemix/docs/set-up-continuous-delivery-ibm-containers/)
- [IBM DevOps Services](http://hub.jazz.net)
- [IBM Bluemix](http://bluemix.net)
