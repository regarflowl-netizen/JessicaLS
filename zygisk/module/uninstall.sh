# Preserve /data/adb/lspd (module config, database, and logs) across routine uninstalls.
rm -f /data/adb/lspd/.cli_sock
if ! pidof jessicalsd >/dev/null 2>&1; then
    rm -f /data/adb/lspd/lock
fi
