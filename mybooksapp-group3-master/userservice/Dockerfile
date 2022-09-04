FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=booksdb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://127.0.0.1:3306/booksdb
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]