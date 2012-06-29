#!/bin/bash 

# Xercer library for XML and SAX functionality 
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY1=$DIRECTORY"/Lib/xerces-2_11_0/resolver.jar:"
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY2=$DIRECTORY"/Lib/xerces-2_11_0/serializer.jar:"
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY3=$DIRECTORY"/Lib/xerces-2_11_0/xercesImpl.jar:"
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY4=$DIRECTORY"/Lib/xerces-2_11_0/xercesSamples.jar:"
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY5=$DIRECTORY"/Lib/xerces-2_11_0/xml-apis.jar:"

# IBM java for XML parser
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY6=$DIRECTORY"/Lib/xml4j.jar:"

# JUNIT library for unit testing
DIRECTORY=$(cd `dirname $0` && pwd)
DIRECTORY7=$DIRECTORY"/Lib/junit-4.10.jar:"



DIRECTORY=$DIRECTORY1$DIRECTORY2$DIRECTORY3$DIRECTORY4$DIRECTORY5$DIRECTORY6$DIRECTORY7
DIRECTORY=$DIRECTORY$CLASSPATH

export set CLASSPATH=$DIRECTORY



javac XMLCustomerTest.java
java XMLCustomerTest Customer.xml

#clear all the unneccessary .class files
rm -rf *.class
