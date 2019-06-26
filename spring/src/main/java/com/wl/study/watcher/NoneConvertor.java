package com.wl.study.watcher;

/**
 * @author: jingzhuo
 * @since: 2018/3/12
 */
public class NoneConvertor implements IArgsConvertor {

    @Override
    public String convert(Object args) {
        return args == null ? null : args.toString();
    }
}
