export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_92.jdk/Contents/Home
export ANT_HOME=`pwd`/../../../tools/apache-ant-1.9.7
export PATH=$JAVA_HOME/bin:$ANT_HOME/bin:$PATH
ant -f ./build.xml
#ant -f ./ant_build.xml compile
