FROM node:8.10.0
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY server.js /usr/src/app
COPY dist/MyBooks /usr/src/app/dist
COPY package-container.json /usr/src/app
RUN mv package-container.json package.json
RUN npm install express --save
EXPOSE 4200
CMD ["node","server.js"]
