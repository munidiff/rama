#!/usr/bin/env bash

# Install jars locally for development purposes

# Install EMF Compare locally
mvn install:install-file \
    -Dfile=org.eclipse.emf.compare_3.5.3.202406060900.jar \
    -DgroupId=org.eclipse.emf.compare \
    -DartifactId=org.eclipse.emf.compare \
    -Dversion=3.5.3 \
    -Dpackaging=jar

# Install Modiff libs locally
mvn install:install-file \
    -Dfile=org.eclipse.epsilon.modiff-0.0.1-SNAPSHOT.jar \
    -DpomFile=org.eclipse.epsilon.modiff-0.0.1-SNAPSHOT.pom.xml

mvn install:install-file \
    -Dfile=org.eclipse.epsilon.modiff.emfcompare-0.0.1-SNAPSHOT.jar \
    -DpomFile=org.eclipse.epsilon.modiff.emfcompare-0.0.1-SNAPSHOT.pom.xml
