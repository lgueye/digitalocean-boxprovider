#!/usr/bin/env bash

java -jar target/digitalocean-boxprovider-0.0.1.jar --request='{"intent": "drop","provider":"digitalocean","specifications":[{"env":"production"},{"env":"staging"}]}' --inventory='tickstack'
