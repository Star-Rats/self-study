package com.pins.filepublisher.service;

import cn.hutool.core.util.RuntimeUtil;
import org.springframework.stereotype.Service;

@Service
public class ShellService extends AbstractOSService{
    @Override
    void restartNginxService(String cmd) {
        RuntimeUtil.execForStr(cmd);
    }
}
