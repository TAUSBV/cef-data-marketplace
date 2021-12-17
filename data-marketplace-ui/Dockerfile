FROM node:12.13.0-alpine as build-stage
RUN apk --no-cache add pkgconfig autoconf automake libtool nasm build-base zlib-dev
ARG environment=development
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY ./ .
RUN npm run build -- --mode ${environment}

FROM nginx as production-stage
RUN mkdir /app
COPY --from=build-stage /app/dist /app
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
