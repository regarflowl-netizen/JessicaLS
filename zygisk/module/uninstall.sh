# Preserve /data/adb/lspd (module config, database, and logs) across routine uninstalls.
rm -f /data/adb/lspd/.cli_sock /data/adb/lspd/lock
