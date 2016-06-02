#!/bin/bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_92.jdk/Contents/Home
export ANT_HOME=../../test525/tools/apache-ant-1.9.7
export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$PATH
$ANT_HOME/bin/ant -f ../../test525/TestNG/build.xml
#ant -f ./ant_build.xml compile
