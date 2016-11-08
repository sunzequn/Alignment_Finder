package com.sunzequn.af.utils;

import com.sunzequn.af.common.Conf;
import com.sunzequn.af.prepare.Triple;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sloriac on 16-9-16.
 */
public class TripleUtil {


    public static Triple parseLine(String line) {
        String[] params = line.trim().split(Conf.SPLIT);
        if (params.length == 3) {
            return new Triple(params[0], params[1], params[2]);
        }
        return null;
    }

    /**
     * 将ttl文件的一行解析为一个三元组
     *
     * @param line
     * @return
     */
    public static Triple parseTTLLine(String line) {
        line = StringUtils.removeEnd(line, ".").trim();
        String[] params = line.split(" ");
        if (params.length == 3) {
            return new Triple(removeBrackets(params[0]), removeBrackets(params[1]), removeBrackets(params[2]));
        }
        return null;
    }

    private static String removeBrackets(String uri) {
        uri = StringUtils.removeEnd(uri.trim(), ">");
        return StringUtils.removeStart(uri, "<").trim();
    }
}
