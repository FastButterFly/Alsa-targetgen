package me.f4stbutterfly.pkg;

import java.util.List;

public class BaseType {
    public final String name;
    public final List<String> args;

    protected BaseType(String a, List<String> as) {
        name = a;
        args = as;
    }
}
