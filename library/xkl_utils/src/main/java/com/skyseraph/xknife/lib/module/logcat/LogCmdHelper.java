package com.skyseraph.xknife.lib.module.logcat;

import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Log cmd helper.
 */
public class LogCmdHelper {

    private static final List<String> DEFAULT_COMMAND = new ArrayList<>();

    static {
        DEFAULT_COMMAND.add("logcat");
    }

    private List<String> commandLine;

    private static LogCmdHelper mInstance = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static LogCmdHelper getInstance() {
        if (mInstance == null) {
            mInstance = new LogCmdHelper();
        }
        return mInstance;
    }

    private LogCmdHelper() {
        commandLine = new ArrayList<>(DEFAULT_COMMAND);
    }

    /**
     * Options log cmd helper.
     *
     * @param options the options
     * @return the log cmd helper
     */
    public LogCmdHelper options(LogParser.Options options) {
        commandLine.add(LogParser.parse(options));
        return this;
    }

    /**
     * Recent lines log cmd helper.
     *
     * @param lineSize the line size
     * @return the log cmd helper
     */
    public LogCmdHelper recentLines(@IntRange(from = 0) int lineSize) {
        commandLine.add("-t");
        commandLine.add(String.valueOf(lineSize));
        return this;
    }

    /**
     * Filter log cmd helper.
     *
     * @param tag log的tag
     * @return the log cmd helper
     */
    @CheckResult
    public LogCmdHelper filter(@NonNull String tag) {
        return filter(tag, LogParser.Level.VERBOSE); // 默认显示所有信息
    }

    /**
     * logcat Tag:I *:S
     *
     * @param tag log的tag 不输入代表仅仅通过lev做筛选
     * @param lev log的级别
     * @return the log cmd helper
     */
    @CheckResult
    public LogCmdHelper filter(@Nullable String tag, LogParser.Level lev) {
        String levStr = LogParser.parse(lev);

        if (!TextUtils.isEmpty(tag)) {
            commandLine.add(tag.trim() + ":" + levStr);
            commandLine.add("*:S");
        } else {
            commandLine.add("*:" + levStr);
        }
        return this;
    }

    /**
     * With time log cmd helper.
     *
     * @return the log cmd helper
     */
    @CheckResult
    public LogCmdHelper withTime() {
        commandLine.add("-v");
        commandLine.add("time");
        return this;
    }

    /**
     * Clear log cmd helper.
     *
     * @return the log cmd helper
     */
    @CheckResult
    public LogCmdHelper clear() {
        commandLine.add("-c");
        return this;
    }

    /**
     * Commit process.
     *
     * @return the process
     */
    public Process commit() {
        Process exec = null;
        try {
            exec = Runtime.getRuntime().exec(commandLine.toArray(new String[this.commandLine.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            commandLine = new ArrayList<>(DEFAULT_COMMAND);
        }
        return exec;
    }

}
