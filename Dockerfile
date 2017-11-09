FROM java:8

# Update OS
RUN apt-get update
# Install maven
RUN apt-get install -y maven
# Install gnuplot
RUN apt-get install -y gnuplot
WORKDIR /project

# Add Files into container
ADD . /project
# Install External Dependencies
RUN cd src/main/resources/ExternalDependencies && sh installDependencies.sh
# Install locally compied jars
RUN mvn install:install-file -Dfile=src/main/resources/ExternalDependencies/GridTLI-1.0.jar -DgroupId=org.cidarlab -DartifactId=GridTLI -Dversion=1.0 -Dpackaging=jar
RUN mvn install:install-file -Dfile=src/main/resources/ExternalDependencies/SMC4STL-0.0.1-SNAPSHOT.jar -DgroupId=SMC4STL -DartifactId=SMC4STL -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
RUN mvn install:install-file -Dfile=src/main/resources/ExternalDependencies/STLSharp.jar -DgroupId=org.cidarlab.bioCPS -DartifactId=STLSharp -Dversion=1.0 -Dpackaging=jar

# Maven Test
RUN mvn test

# Maven Build
RUN mvn dependency:copy-dependencies

# Compile into jar
RUN mvn package
# Open Port
EXPOSE 9090

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-cp", "target/alphaPhoenix-1.0.jar:dependency", "org.cidarlab.phoenix.adaptors.spring.Application"]
