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
RUN cd lib/externalDependencies && sh installDependencies.sh

RUN mvn clean install

# Open Port
EXPOSE 9090