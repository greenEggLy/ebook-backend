#!/bin/zsh

source ~/.zshrc
# Start the database
mysql.server start
# Start the zookeeper
zookeeper_start &
# Start the kafka
kafka_start &