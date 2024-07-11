#!/bin/sh
# wait-for.sh

set -e

host="$1"
shift
cmd="$@"

until mysql -h "$host" -u "${SPRING_DATASOURCE_USERNAME}" -p"${SPRING_DATASOURCE_PASSWORD}" -e 'select 1'; do
  >&2 echo "MySQL is unavailable - sleeping"
  sleep 1
done

>&2 echo "MySQL is up - executing command"
exec $cmd
