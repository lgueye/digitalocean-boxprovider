#!/usr/bin/env bash

java -jar target/digitalocean-boxprovider-0.0.1.jar --request='{"intent":"create","provider":"digitalocean","image":"ubuntu-16-04-x64","region":"fra1","specifications":[{"env":"production","boxes":[{"name":"influxdb","size":"1gb"},{"name":"telegraf","size":"1gb"},{"name":"chronograf","size":"1gb"},{"name":"kapacitor","size":"1gb"}]},{"env":"staging","boxes":[{"name":"telegraf","size":"1gb"},{"name":"chronograf","size":"2gb"},{"name":"kapacitor","size":"1gb"},{"name":"influxdb","size":"1gb"}]}]}' --inventory='tickstack'
