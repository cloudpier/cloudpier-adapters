cloudpier-adapters
==================
Cloud Pier Remote Adapters

This Repository includes source code of Java implementations of some Remote Adapters used by Cloud Pier.
This code is not being used directly from Cloud Pier Core and Cloud Pier UI. However the .war files that are created by building these standalone projects are used from Cloud Pier Core project in order to be deployed to each coresponding PaaS. 

# Current Adapters:
## BeanstalkAdapter
Remote Adapter source code for AWS Elastic Beanstalk PaaS
## CloudBeesAdapter
Remote Adapter source code for CloudBees Run@Cloud PaaS
## CloudFoundryC4SAdapter
Remote Adapter source code for CloudFoundry(v1) PaaS
## OpenShiftAdapter
Remote Adapter source code for Red Hat OpenShift PaaS


# Usage
## Examples of Cloud Pie Remote Adapters
The main purpose of these project is to be examples of how a Cloud Pier Adapter can be created. More information about making and integrating a Cloud Pier adapter for a new PaaS can be found in the Wiki.

## Deployment
All Remote Adapters are using Maven. So building of each adapter if needed can be done by executing.
Compile:

    mvn clean install

After the build a .war file is generated in folder target.
