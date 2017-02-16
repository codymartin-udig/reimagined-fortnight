FROM httpd:2.4
# COPY httpd.conf /usr/local/apache2/conf
# COPY httpd-vhosts.conf /usr/local/apache2/conf/extra
# docker network connect knight-instance chess_network
RUN mkdir -p /var/www
COPY content /var/www
COPY content /usr/local/apache2/htdocs