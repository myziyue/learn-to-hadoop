package com.myziyue.hadoop.ch03.ex07;

import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.fs.Path;

public class RegexExcludePathFilter implements PathFilter {
    private final String regex;

    public RegexExcludePathFilter(String regex){
        this.regex = regex;
    }

    public boolean accept(Path path){
        return !path.toString().matches(regex);
    }
}
