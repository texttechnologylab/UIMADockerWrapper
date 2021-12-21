#!/bin/sh
cd container_explorer && flutter build web && cd .. && rm -rf web && cp -r container_explorer/build/web web
tar -cvjSf framework.tar.bz2 src web pom.xml
