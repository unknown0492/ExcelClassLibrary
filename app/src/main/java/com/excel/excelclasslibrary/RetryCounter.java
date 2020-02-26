package com.excel.excelclasslibrary;

public class RetryCounter {
    String property_name;

    public RetryCounter(String property_name2) {
        this.property_name = property_name2;
    }

    public void setRetryCount(int count) {
        StringBuilder sb = new StringBuilder();
        sb.append("setprop ");
        sb.append(this.property_name);
        sb.append(" ");
        sb.append(count);
        UtilShell.executeShellCommandWithOp(sb.toString());
    }

    public int getRetryCount() {
        StringBuilder sb = new StringBuilder();
        sb.append("getprop ");
        sb.append(this.property_name);
        String c = UtilShell.executeShellCommandWithOp(sb.toString()).trim();
        if (c.equals("") || c.equals("0")) {
            setRetryCount(1);
            return 1;
        }
        int count = Integer.parseInt(c) + 1;
        setRetryCount(count);
        return count;
    }

    public long getRetryTime() {
        return retryTimerIncrementer(getRetryCount());
    }

    public void reset() {
        StringBuilder sb = new StringBuilder();
        sb.append("setprop ");
        sb.append(this.property_name);
        sb.append(" 0");
        UtilShell.executeShellCommandWithOp(sb.toString());
    }

    public static long retryTimerIncrementer(int try_count) {
        if (try_count == 0) {
            return 10000;
        }
        if (try_count == 1) {
            return 60000;
        }
        if (try_count == 2) {
            return 120000;
        }
        if (try_count != 3) {
            return 600000;
        }
        return 300000;
    }
}
