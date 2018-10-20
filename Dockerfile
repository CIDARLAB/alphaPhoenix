FROM java:8

# Update OS
RUN apt-get update

# Install maven
RUN apt-get install -y maven

# Install gnuplot
RUN apt-get install -y gnuplot

# Install Python Tools
RUN apt-get install -y python-setuptools python-dev build-essential
RUN easy_install pip

# Add Project Files
WORKDIR /project
ADD . /project

#DNA Plot Lib
RUN git clone https://github.com/VoigtLab/dnaplotlib.git
ENV	PYTHONPATH $PYTHONPATH/project/dnaplotlib/
RUN pip install matplotlib
# Test dnaplotlib
RUN python -c 'import dnaplotlib'

# Install External Dependencies
RUN cd lib/externalDependencies && sh installDependencies.sh

RUN mvn clean install -DskipTests

# Open Port
EXPOSE 9090

# Launch Application
CMD mvn exec:java -Dexec.mainClass="org.cidarlab.phoenix.adaptors.spring.Application"
