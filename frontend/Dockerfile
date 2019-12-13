FROM nginx:alpine

EXPOSE 80

RUN rm /etc/nginx/conf.d/default.conf

COPY src/main/nginx/00_blog-frontend.nginx /etc/nginx/conf.d/00_blog-frontend.conf

COPY ./target/prod-web /usr/share/blog/html
