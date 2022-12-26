package com.yyh.wubida.utils;

import com.yyh.wubida.common.utils.IdWorker;

public class IdUtils {
    private static final IdWorker ID_WORKER = new IdWorker();

    public static String get() {
        return String.valueOf(ID_WORKER.nextId());
    }
}
