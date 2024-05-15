FROM gradle:jdk21-alpine
ARG PRODUCTION
ARG JDBC_DATABASE_PASSWORD
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME

ENV PRODUCTION ${PRODUCTION_STAGING}
ENV JDBC_DATABASE_PASSWORD ${JDBC_DATABASE_PASSWORD_STAGING}
ENV JDBC_DATABASE_URL ${JDBC_DATABASE_URL_STAGING}
ENV JDBC_DATABASE_USERNAME ${JDBC_DATABASE_USERNAME_STAGING}

WORKDIR /app
COPY ./review*-0.0.1-SNAPSHOT.jar /app
RUN ls -la
EXPOSE 8080
CMD ["java","-jar","review-keranjang-service-0.0.1-SNAPSHOT.jar"]
