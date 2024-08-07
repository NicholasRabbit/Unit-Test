package com.tdd.practice.refactor.parser;

import com.tdd.practice.annotation.Option;

import java.util.List;

public interface OptionParser {
    Object parse(List<String> arguments, Option option);
}
