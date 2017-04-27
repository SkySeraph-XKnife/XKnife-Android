package com.skyseraph.xknife.lib.module.logcat;

import android.support.annotation.CheckResult;


/**
 * The type Log parser.
 */
public class LogParser {

    /**
     * The enum Options.
     */
    public enum Options {

        SILENT,
        FILE,
        BYTES,
        COUNT,
        FORMAT,
        CLEAR,
        DUMP
    }

    /**
     * The enum Level.
     */
    public enum Level {

        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL,
        ASSERT
    }

    /**
     * The constant VERBOSE.(明细)
     */
    public static final String VERBOSE = "V";

    /**
     * The constant DEBUG. (调试)
     */
    public static final String DEBUG = "D";

    /**
     * The constant INFO. (信息)
     */
    public static final String INFO = "I";

    /**
     * The constant WARN. (警告)
     */
    public static final String WARN = "W";

    /**
     * The constant ERROR.(错误)
     */
    public static final String ERROR = "E";

    /**
     * The constant FATAL. (严重错误)
     */
    public static final String FATAL = "F";

    /**
     * The constant ASSERT.Silent(Super all output) (最高的优先级, 前所未有的错误);
     */
    public static final String ASSERT = "S";


    /**
     * The constant SILENT. Set default filter to SILENT. Like '*:s'
     */
    public static final String SILENT = "-s";

    /**
     * The constant FILE. <filename>   Log to FILE. Default to stdout
     */
    public static final String FILE = "-f";

    /**
     * The constant BYTES. Rotate log every bytes(k). (16 if unspecified). Requires -f
     */
    public static final String BYTES = "-r";

    /**
     * The constant COUNT. Sets max number of rotated logs to <count>, default 4
     */
    public static final String COUNT = "-n";

    /**
     * The constant FORMAT. Sets the log print format, where  is one of: brief process tag thread raw time
     */
    public static final String FORMAT = "-v";

    /**
     * The constant CLEAR. clear (flush) the entire log and e // thread time
     */
    public static final String CLEAR = "-c";

    /**
     * The constant DUMP. dump the log and then exit (don't block)  logcat获取日志完毕后终止进程
     */
    public static final String DUMP = "-d";

    /**
     * Parse string.
     *
     * @param options the options
     * @return the string
     */
    @CheckResult
    public static String parse(Options options) {
        switch (options) {
            case SILENT:
                return SILENT;
            case FILE:
                return FILE;
            case BYTES:
                return BYTES;
            case COUNT:
                return COUNT;
            case FORMAT:
                return FORMAT;
            case CLEAR:
                return CLEAR;
            case DUMP:
                return DUMP;
            default:
                return DUMP;
        }
    }

    /**
     * Parse string.
     *
     * @param level the level
     * @return the string
     */
    @CheckResult
    public static String parse(Level level) {
        switch (level) {
            case VERBOSE:
                return VERBOSE;
            case DEBUG:
                return DEBUG;
            case INFO:
                return INFO;
            case WARN:
                return WARN;
            case ERROR:
                return ERROR;
            case FATAL:
                return FATAL;
            case ASSERT:
                return ASSERT;
            default:
                return ASSERT;
        }
    }

    /**
     * Parse lev log utils . level.
     *
     * @param level the level
     * @return the log utils . level
     */
    @CheckResult
    public static Level parseLev(String level) {
        switch (level) {
            case VERBOSE:
                return Level.VERBOSE;
            case DEBUG:
                return Level.DEBUG;
            case INFO:
                return Level.INFO;
            case WARN:
                return Level.WARN;
            case ERROR:
                return Level.ERROR;
            case FATAL:
                return Level.FATAL;
            case ASSERT:
                return Level.ASSERT;
            default:
                return Level.ASSERT;
        }
    }

}
