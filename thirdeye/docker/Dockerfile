FROM centos:7

ARG PINOT_BRANCH=rzp
ARG MAVEN_VERSION=3.6.3
ARG SHA=d941423d115cd021514bfd06c453658b1b3e39e6240969caf4315ab7119a77299713f14b620fb2571a264f8dff2473d8af3cb47b05acf0036fc2553199a5c1ee
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
ARG PINOT_GIT_URL="https://github.com/utk-spartan/incubator-pinot.git"
ENV TE_HOME=/opt/thirdeye
ENV TE_BUILD_DIR=/opt/thirdeye-build


RUN echo $'[BellSoft] \n\
name=BellSoft Repository \n\
baseurl=http://yum.bell-sw.com \n\
enabled=1 \n\
gpgcheck=1 \n\
gpgkey=https://download.bell-sw.com/pki/GPG-KEY-bellsoft \n\
priority=1' > /etc/yum.repos.d/bellsoft.repo \
    && yum update -y \
    && yum install bellsoft-java8 vim wget curl git gcc-c++ make -y

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
    && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
    && rm -f /tmp/apache-maven.tar.gz \
    && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

RUN echo '{ "allow_root": true }' > /root/.bowerrc \
    && curl -sL https://rpm.nodesource.com/setup_10.x | bash - \
    && yum install nodejs -y \
    && npm install -g phantomjs --unsafe-perm --ignore-scripts

RUN git clone ${PINOT_GIT_URL} ${TE_BUILD_DIR} --single-branch --branch ${PINOT_BRANCH} --depth=1 \
    && cd ${TE_BUILD_DIR}/thirdeye \
    && mvn clean install -X -DskipTests || exit 1 \
    && mkdir -p ${TE_HOME}/config \
    && mkdir -p ${TE_HOME}/bin \
    && cp -rp ${TE_BUILD_DIR}/thirdeye/thirdeye-pinot/config/* ${TE_HOME}/config/. \
    && cp ${TE_BUILD_DIR}/thirdeye/thirdeye-pinot/target/thirdeye-pinot-1.0.0-SNAPSHOT.jar ${TE_HOME}/bin/. \
    && rm -rf ${TE_BUILD_DIR}


VOLUME ["${TE_HOME}/config"]
EXPOSE 1426 1427
WORKDIR ${TE_HOME}

COPY entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]