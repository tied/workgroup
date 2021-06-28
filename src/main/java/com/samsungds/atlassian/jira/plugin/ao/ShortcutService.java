package com.samsungds.atlassian.jira.plugin.ao;

import com.atlassian.activeobjects.tx.Transactional;

import java.util.List;

@Transactional
public interface ShortcutService {
    Shortcut add(ShortcutVo shortcutVo);
    List<Shortcut> getAllByUserName(String userName);
    Shortcut modify(ShortcutVo shortcutVo);
    Shortcut remove(Long id);
}
