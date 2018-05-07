#!/usr/bin/env bash

cd ../ontheio/ &&
mvn clean install -Dconfiguration="$1" -Dbrowser="$2"
cd ../ontheio/target &&
allure generate --clean
timestamp=`date '+%Y_%m_%d__%H_%M_%S'` &&
zip -r $timestamp.zip ../target/allure-report